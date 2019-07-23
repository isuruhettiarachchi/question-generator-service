package cdap.questiongenerator;

import cdap.questiongenerator.question.Question;
import cdap.questiongenerator.question.SimpleQuestion;
import cdap.questiongenerator.transducer.QuestionTransducer;
import cdap.questiongenerator.transformation.InitialTransformationStep;
import cdap.questiongenerator.utilities.AnalysisUtilities;
import edu.stanford.nlp.trees.Tree;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionGeneratorService {
    private QuestionTransducer qt;
    private InitialTransformationStep trans;
    private  QuestionRanker qr = null;

    private Tree parsed;

    Integer maxLength = 1000;
    boolean justWH = false;

    boolean avoidFreqWords = false;
    boolean preferWH = false;
    boolean downweightPronouns = false;

    String modelPath = null;

    List<Question> outputQuestionList = new ArrayList<Question>();

    public List<SimpleQuestion> generateQuestions(String document) {

        if(modelPath != null){
            System.err.println("Loading question ranking models from "+modelPath+"...");
            qr = new QuestionRanker();
            qr.loadModel(modelPath);
        }

        qt = new QuestionTransducer();
        trans = new InitialTransformationStep();

        System.out.println(document);

        List<SimpleQuestion> res = new ArrayList<SimpleQuestion>();

        outputQuestionList.clear();

        qt.setAvoidPronounsAndDemonstratives(false);

        AnalysisUtilities.getInstance();

        long startTime = System.currentTimeMillis();

        List<String> sentences = AnalysisUtilities.getSentences(document);

        List<Tree> inputTrees = new ArrayList<Tree>();

        for (String sentence: sentences) {
//            System.err.println("Question Asker: sentence: "+sentence);

            parsed = AnalysisUtilities.getInstance().parseSentence(sentence).parse;
            inputTrees.add(parsed);
        }

        System.err.println("Seconds Elapsed Parsing:\t"+((System.currentTimeMillis()-startTime)/1000.0));

        //step 1 transformations
        List<Question> transformationOutput = trans.transform(inputTrees);

        //step 2 question transducer
        for(Question t: transformationOutput){
            if(GlobalProperties.getDebug()) System.err.println("Stage 2 Input: "+t.getIntermediateTree().yield().toString());
            qt.generateQuestionsFromParse(t);
            outputQuestionList.addAll(qt.getQuestions());
        }

        if(qr != null){
            qr.scoreGivenQuestions(outputQuestionList);
            boolean doStemming = true;
            QuestionRanker.adjustScores(outputQuestionList, inputTrees, avoidFreqWords, preferWH, downweightPronouns, doStemming);
            QuestionRanker.sortQuestions(outputQuestionList, false);
        }

        for (Question question: outputQuestionList) {
            if (question.getTree().getLeaves().size() > maxLength) {
                continue;
            }

            if (justWH && question.getFeatureValue("whQuestion") != 1.0) {
                continue;
            }

            Tree ansTree = question.getAnswerPhraseTree();
            String answer = null;

            if (ansTree != null) {
                answer = AnalysisUtilities.getCleanedUpYield(question.getAnswerPhraseTree());
            }

            SimpleQuestion simpleQuestion = new SimpleQuestion(question.yield(), answer);

            if(answer != null && !AnalysisUtilities.filterGenericWhQuestions(answer)) {
                res.add(simpleQuestion);
            }

        }

        return res;
    }

}

package cdap.questiongenerator.question;

import java.util.List;

public class SimpleQuestion {
    private int questionNo;
    private String questionBody;
    private List<Answer> answers;
    private String correctAnswer;

    public SimpleQuestion(int questionNo, String questionBody, List<Answer> answers, String correctAnswer) {
        this.questionNo = questionNo;
        this.questionBody = questionBody;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }
//    public SimpleQuestion(int questionNo, String questionBody, List<Answer> answers) {
//        this.questionNo = questionNo;
//        this.questionBody = questionBody;
//        this.answers = answers;
//    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}

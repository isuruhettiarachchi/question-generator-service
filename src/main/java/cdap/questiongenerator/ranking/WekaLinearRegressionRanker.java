package cdap.questiongenerator.ranking;

import cdap.questiongenerator.GlobalProperties;
import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

import java.util.Collections;
import java.util.List;

public class WekaLinearRegressionRanker extends BaseRanker implements IRanker {
    private FastVector wekaAttributes;
    private Classifier classifier;
    private Instances defaultInstances;

    private static final long serialVersionUID =  -2634242092831187354L;

    public WekaLinearRegressionRanker(){
        setParameter("regularizer", 0.0001);
    }

    @Override
    public void train(List<List<Rankable>> trainData) {
        int numFeatures = trainData.get(0).get(0).features.length;

        Instances instances = new Instances("rating", wekaAttributes(numFeatures), 0);
        instances.setClassIndex(instances.numAttributes()-1);

        classifier = createClassifierObject();

        for(int i=0;i<trainData.size();i++){
            for(int j=0;j<trainData.get(i).size();j++){
                Rankable point = trainData.get(i).get(j);
                Instance inst = makeInstance(getDefaultInstances(numFeatures), point.features, point.label);
                instances.add(inst);
            }
        }

        try {
            classifier.buildClassifier(instances);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if(GlobalProperties.getDebug()) System.err.println(classifier.toString());
    }

    @Override
    public void rank(List<Rankable> unranked, boolean doStort) {
        for (Rankable r: unranked) {
            r.score = predict(r);
        }

        if (doStort) {
            Collections.sort(unranked);
            Collections.reverse(unranked);
        }
    }

    private Classifier createClassifierObject() {
        Classifier res;


        //res = new SMOreg();


        String[] opts;
        res = new LinearRegression();
        //((LinearRegression)res).turnChecksOff(); //option to turn off standardization of feature values
        opts = new String[5];
        //opts = new String[6];
        opts[0] = "-R";
        opts[1] = ((Double)getParameter("regularizer")).toString();
        opts[2] = "-S";
        opts[3] = "1"; //no attribute selection
        opts[4] = "-C"; //do not try to eliminate colinear attributes
        //opts[5] = "-D";

        try {
            res.setOptions(opts);
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        return res;
    }

    private Instance makeInstance(Instances dataset, double [] featureValues, double labelValue){
        Instance res = null;
        res = new Instance(featureValues.length+1);
        res.setDataset(dataset);
        for(int i=0; i<featureValues.length; i++){
            res.setValue(i, featureValues[i]);
        }

        res.setClassValue(labelValue);


        return res;
    }

    private Instances getDefaultInstances(int numFeatures){
        if(defaultInstances == null){
            defaultInstances = new Instances("default", wekaAttributes(numFeatures), 0);
            defaultInstances.setClassIndex(defaultInstances.numAttributes()-1);
        }
        return defaultInstances;
    }

    public Double predict(Rankable point){
        Double res = 0.0;
        Instance inst;

        inst = makeInstance(getDefaultInstances(point.features.length), point.features, 0.0);

        try {
            res = classifier.classifyInstance(inst);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    protected FastVector wekaAttributes(int numFeatures) {
        if(wekaAttributes == null){
            wekaAttributes = new FastVector();
            Attribute att;
            //System.err.println("NUM FEATURES "+ numFeatures + "\t"+Question.getFeatureNames().size());
            for(int i=0; i<numFeatures; i++){
                att = new Attribute("feature"+i);
                //att = new Attribute(Question.getFeatureNames().get(i));
                wekaAttributes.addElement(att);
            }

            att = new Attribute("class");
            wekaAttributes.addElement(att);
        }

        return wekaAttributes;
    }
}

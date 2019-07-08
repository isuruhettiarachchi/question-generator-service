package cdap.questiongenerator.ranking;

public class RankFactory {
    public static IRanker createRanker(String type){
        IRanker res;

        //took out other options for simplicity
        res = new WekaLinearRegressionRanker();

        return res;
    }
}

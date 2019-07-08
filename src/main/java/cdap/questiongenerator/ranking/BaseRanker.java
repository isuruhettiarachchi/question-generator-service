package cdap.questiongenerator.ranking;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseRanker implements IRanker, Serializable {
    private static final long serialVersionUID = -160210985855397618L;
    private Map<String, Double> params;

    protected BaseRanker() {
        params = new HashMap<String, Double>();
    }

    public void rank(List<Rankable> unranked) {
        rank(unranked, true);
    }

    public abstract void rank(List<Rankable> unranked, boolean doStort);

    public void rankAll(Collection<List<Rankable>> unrankedCollections) {
        rankAll(unrankedCollections, true);
    }

    public void setParameter(String key, Double value) {
        params.put(key, value);
    }

    public Double getParameter(String key) {
        return params.get(key);
    }

    public void rankAll(Collection<List<Rankable>> unrankedCollections, boolean doSort) {
        for (List<Rankable> unranked: unrankedCollections) {
            rank(unranked, doSort);
        }
    }

}

package cdap.controller;

import cdap.questiongenerator.transformation.SentenceSimplifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SentenceSimplifierHttpController {

    public List<String> simplifySentences(String transcript) {
        List<String> res = new ArrayList<String>();

        SentenceSimplifier simplifier = new SentenceSimplifier();
        simplifier.setExtractFromVerbComplements(false);
        simplifier.setBreakNPs(false);


        return res;
    }
}

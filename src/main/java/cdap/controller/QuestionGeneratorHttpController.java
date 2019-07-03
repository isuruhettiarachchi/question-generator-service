package cdap.controller;

import cdap.questiongenerator.QuestionGeneratorService;
import cdap.questiongenerator.question.SimpleQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionGeneratorHttpController {

    private QuestionGeneratorService service;

    @Autowired
    QuestionGeneratorHttpController(QuestionGeneratorService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "Application works";
    }

    @PostMapping("/generate")
    public List<SimpleQuestion> generateQuestions(@RequestBody String document) {
        return service.generateQuestions(document);
    }
}

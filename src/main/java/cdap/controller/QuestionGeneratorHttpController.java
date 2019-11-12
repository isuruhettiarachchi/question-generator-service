package cdap.controller;

import cdap.entities.QuestionGeneratorRequest;
import cdap.entities.Questions;
import cdap.questiongenerator.QuestionGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

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
    public String generateQuestions(@RequestBody QuestionGeneratorRequest request) {
        CompletableFuture.runAsync(() -> {
            service.generateQuestions(request.getDocument(), request.getLectureId());
        });

        return "Request is processing";
//        return service.generateQuestions(request.getDocument(), request.getLectureId());
    }
}

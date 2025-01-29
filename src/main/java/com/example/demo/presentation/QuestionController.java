package com.example.demo.presentation;
import com.example.demo.domain.entity.GroupedQuestion;
import com.example.demo.domain.service.QuestionService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping(value = "/add-question")
    public void addQuestion(@RequestBody QuestionRequest request) {

        questionService.registerQuestion(request);
    }

    @GetMapping(value = "/get-questions")
    public List<GroupedQuestion> getQuestion(@RequestParam int userId, @RequestParam int categoryId) {
        return questionService.getQuestion(userId, categoryId);
        
    }
}
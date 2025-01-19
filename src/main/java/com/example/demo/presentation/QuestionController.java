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
    public void addQuestion(@RequestBody Map<String, Object> requestBody) {

        // 値の取得と型キャスト
        String categoryName = (String) requestBody.get("category");
        int userId = (int) requestBody.get("userId");
        String question = (String) requestBody.get("question");

        // answers を List<String> として取得
        List<String> choices = (List<String>) requestBody.get("choicesData");

        // correctAnswerId を Integer にキャスト
        int correctedAnswer = (int) ((Number) requestBody.get("correctAnswerId")).intValue();

        String explanation = (String) requestBody.get("explanation");

        questionService.registerQuestion(categoryName, userId, question, choices, correctedAnswer, explanation);
    }

    @GetMapping(value = "/get-questions")
    public List<GroupedQuestion> getQuestion(@RequestParam int userId, @RequestParam int categoryId) {
        return questionService.getQuestion(userId, categoryId);
        
    }
}
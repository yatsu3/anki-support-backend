package com.example.demo.presentation;
import com.example.app.application.dto.QuestionResponse;
import com.example.demo.domain.entity.GroupedQuestion;
import com.example.demo.domain.service.QuestionService;
import com.example.demo.infrastructure.dto.QuestionDto;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public List<GroupedQuestion> getQuestion(@RequestParam String uuid, @RequestParam int categoryId) {
        return questionService.getQuestion(uuid, categoryId);
        
    }

    @GetMapping(value = "/question-list")
    public List<QuestionDto> getQuestionList(@RequestParam String uuid, @RequestParam int categoryId) {
        return questionService.getQuestionList(uuid, categoryId);
        
    }

    @GetMapping("/questions/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestionInfo(@RequestHeader("uuid") String uuid, @PathVariable int questionId) {
        QuestionResponse response = questionService.getQuestionById(questionId, uuid);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/questions/{questionId}")
    public void updateQuestion(@RequestBody UpdateQuestionRequest request) {
        questionService.updateQuestion(request);
    }


}
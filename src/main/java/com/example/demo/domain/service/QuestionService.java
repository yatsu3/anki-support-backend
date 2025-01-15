package com.example.demo.domain.service;
import com.example.demo.domain.entity.Question;
import com.example.demo.infrastructure.repository.CategoryRepository;
import com.example.demo.infrastructure.repository.QuestionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ChoiceService choiceService;
    
    public void registerQuestion(String categoryName, int userId, String questionContent, List<String> choices, int correctedAnswer, String explanation) {

        int questionId = questionRepository.getNextId();
        int categoryId = categoryRepository.getIdByCategoryName(categoryName);
        Question question = new Question(questionId, userId, categoryId, questionContent, explanation, correctedAnswer);

        questionRepository.registerQuestion(question);
        
        choiceService.registerChoice(choices, questionId);

    }
}
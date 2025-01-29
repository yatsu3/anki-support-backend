package com.example.demo.domain.service;
import com.example.demo.domain.entity.Choice;
import com.example.demo.infrastructure.repository.AnswerRepository;
import com.example.demo.infrastructure.repository.ChoiceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;
    
    public void registerAnswer(int answerId, int questionId, int correctAnswer) {
        
        answerRepository.registerAnswer(answerId, questionId, correctAnswer);

    }
}
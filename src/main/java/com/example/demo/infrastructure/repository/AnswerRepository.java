package com.example.demo.infrastructure.repository;
import com.example.demo.domain.entity.Choice;
import com.example.demo.infrastructure.mapper.AnswerMapper;
import com.example.demo.infrastructure.mapper.ChoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AnswerRepository {

    @Autowired
    AnswerMapper mapper;

    public int getNextId() {
        return mapper.getNextId() + 1;
    }

    public void registerAnswer(int answerId, int questionId, int correctAnswer) {
        mapper.insertAnswer(answerId, questionId, correctAnswer);
    }

}
package com.example.demo.infrastructure.repository;
import com.example.demo.domain.entity.Question;
import com.example.demo.infrastructure.dto.GroupedQuestionDto;
import com.example.demo.infrastructure.dto.GroupedQuestionDto;
import com.example.demo.infrastructure.dto.QuestionDto;
import com.example.demo.infrastructure.dto.QuestionInfoDto;
import com.example.demo.infrastructure.mapper.QuestionMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class QuestionRepository {

    @Autowired
    QuestionMapper mapper;

    public int getNextId() {
        return mapper.getNextId() + 1;
    }

    public void registerQuestion(Question question) {
        mapper.insertQuestion(question);
    }

    public List<GroupedQuestionDto> getQuestion(int userId, int categoryId) {
        return mapper.getQuestion(userId, categoryId);
    }

    public List<QuestionDto> getQuestionList(int userId, int categoryId) {
        return mapper.getQuestionList(userId, categoryId);
    }

    public QuestionInfoDto findQuestionById(int questionId, int userId) {
        return mapper.findQuestionById(questionId, userId);
    }

    public void updateQuestion(Question question) {
        mapper.updateQuestion(question);
    }

    public void updateQuestionInfo(int questionId, String questionContent, String explanation) {
        mapper.updateQuestionInfo(questionId, questionContent, explanation);
    }

}
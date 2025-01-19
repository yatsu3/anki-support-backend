package com.example.demo.infrastructure.repository;
import com.example.demo.domain.entity.Question;
import com.example.demo.infrastructure.dto.QuestionDto;
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

    public List<QuestionDto> getQuestion(int userId, int categoryId) {
        return mapper.getQuestion(userId, categoryId);
    }

}
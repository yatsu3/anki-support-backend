package com.example.demo.infrastructure.repository;
import com.example.demo.domain.entity.Choice;
import com.example.demo.infrastructure.dto.ChoiceDto;
import com.example.demo.infrastructure.mapper.ChoiceMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ChoiceRepository {

    @Autowired
    ChoiceMapper mapper;

    public int getNextId() {
        return mapper.getNextId() + 1;
    }

    public void registerChoice(Choice choice) {
        mapper.insertChoice(choice);
    }

    public List<ChoiceDto> searchChoicesByQuestionId(int questionId) {
        return mapper.searchChoicesByQuestionId(questionId);
    }

    public void updateChoice(ChoiceDto choice, int questionId) {
        mapper.updateChoice(choice, questionId);
    }

    public void deleteChoice(int choiceId) {
        mapper.deleteChoice(choiceId);
    }

}
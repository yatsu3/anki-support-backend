package com.example.demo.domain.service;
import com.example.demo.domain.entity.Choice;
import com.example.demo.infrastructure.dto.ChoiceDto;
import com.example.demo.infrastructure.dto.ChoiceDto;
import com.example.demo.infrastructure.dto.ChoiceDto;
import com.example.demo.infrastructure.repository.ChoiceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ChoiceService {

    @Autowired
    ChoiceRepository choiceRepository;
    
    public void registerChoice(List<String> choices, int questionId) {
        
        for(String choiceContent : choices) {
            int choiceId = choiceRepository.getNextId();
            Choice choice = new Choice(choiceId, questionId, choiceContent);
            choiceRepository.registerChoice(choice);
        }

    }

    public List<ChoiceDto> searchChoicesByQuestionId(int questionId) {
        return choiceRepository.searchChoicesByQuestionId(questionId);
    }

    public void updateChoice(ChoiceDto choiceDto, int questionId) {

        choiceRepository.updateChoice(choiceDto, questionId);
    }

    public void deleteChoice(int choiceId) {
        choiceRepository.deleteChoice(choiceId);
    }



}
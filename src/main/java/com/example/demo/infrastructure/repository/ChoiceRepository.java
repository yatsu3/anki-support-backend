package com.example.demo.infrastructure.repository;
import com.example.demo.domain.entity.Choice;
import com.example.demo.infrastructure.mapper.ChoiceMapper;
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

}
package com.example.demo.infrastructure.mapper;
import com.example.demo.domain.entity.Choice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChoiceMapper {

    @Select("""
    SELECT COALESCE(MAX(CHOICE_ID), 0) FROM CHOICES
    """)
    int getNextId();
    
    @Insert("""
    INSERT INTO CHOICES (CHOICE_ID, QUESTION_ID, CHOICE_CONTENT, CREATED_DATETIME, UPDATED_DATETIME) VALUES (#{choice.choiceId}, #{choice.questionId}, #{choice.choiceContent}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
    )
    """)
    void insertChoice(@Param("choice") Choice choice);
}
package com.example.demo.infrastructure.mapper;
import com.example.demo.domain.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuestionMapper {

    @Select("""
    SELECT COALESCE(MAX(QUESTION_ID), 0) FROM QUESTIONS
    """)
    int getNextId();
    
    @Insert("""
    INSERT INTO QUESTIONS (QUESTION_ID, USER_ID, CATEGORY_ID, QUESTION_CONTENT, CORRECTED_ANSWER, EXPLANATION, CREATED_DATETIME, UPDATED_DATETIME) VALUES (#{question.questionId}, #{question.userId}, #{question.categoryId}, #{question.questionContent}, #{question.correctedAnswer}, #{question.explanation},CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
    )
    """)
    void insertQuestion(@Param("question") Question question);
}
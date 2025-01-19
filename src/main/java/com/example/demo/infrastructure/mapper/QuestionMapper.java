package com.example.demo.infrastructure.mapper;
import com.example.demo.domain.entity.Question;
import com.example.demo.infrastructure.dto.QuestionDto;
import java.util.List;
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

    @Select("""
    SELECT q.QUESTION_ID, q.QUESTION_CONTENT, choices.CHOICE_CONTENT,
    q.CORRECTED_ANSWER, q.EXPLANATION
    FROM 
        QUESTIONS q
    LEFT JOIN 
        CHOICES choices
    ON 
        q.QUESTION_ID = choices.QUESTION_ID
    WHERE 
        q.USER_ID = #{userId} AND q.CATEGORY_ID = #{categoryId}
    GROUP BY 
        q.QUESTION_ID, choices.CHOICE_CONTENT, q.CORRECTED_ANSWER, q.EXPLANATION;
    """)
    List<QuestionDto> getQuestion(@Param("userId") int userId, @Param("categoryId") int cagegoryId);
}
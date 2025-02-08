package com.example.demo.infrastructure.mapper;
import com.example.demo.domain.entity.Question;
import com.example.demo.infrastructure.dto.GroupedQuestionDto;
import com.example.demo.infrastructure.dto.QuestionDto;
import com.example.demo.infrastructure.dto.QuestionInfoDto;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface QuestionMapper {

    @Select("""
    SELECT COALESCE(MAX(QUESTION_ID), 0) FROM QUESTIONS
    """)
    int getNextId();
    
    @Insert("""
    INSERT INTO QUESTIONS (QUESTION_ID, USER_ID, CATEGORY_ID, ANSWER_ID, QUESTION_CONTENT, EXPLANATION, CREATED_DATETIME, UPDATED_DATETIME) VALUES (#{question.questionId}, #{question.userId}, #{question.categoryId}, #{question.answerId}, #{question.questionContent}, #{question.explanation},CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
    )
    """)
    void insertQuestion(@Param("question") Question question);

    @Select("""
    SELECT q.QUESTION_ID, q.QUESTION_CONTENT, choices.CHOICE_CONTENT,
    a.ANSWER_CONTENT, q.EXPLANATION
    FROM 
        QUESTIONS q
    LEFT JOIN 
        CHOICES choices
    ON 
        q.QUESTION_ID = choices.QUESTION_ID
    LEFT JOIN
        ANSWERS a
    ON a.ANSWER_ID = q.ANSWER_ID
    WHERE 
        q.USER_ID = #{userId} AND q.CATEGORY_ID = #{categoryId}
    GROUP BY 
        q.QUESTION_ID, choices.CHOICE_CONTENT, a.ANSWER_CONTENT,q.EXPLANATION;
    """)
    List<GroupedQuestionDto> getQuestion(@Param("userId") int userId, @Param("categoryId") int cagegoryId);

    @Select("""
        SELECT QUESTION_ID, QUESTION_CONTENT FROM QUESTIONS
        WHERE CATEGORY_ID = #{categoryId} AND USER_ID = #{userId}
    """)
    List<QuestionDto> getQuestionList(@Param("userId") int userId, @Param("categoryId") int cagegoryId);


    @Select("""
    SELECT QUESTION_ID, QUESTION_CONTENT, EXPLANATION
    FROM QUESTIONS
    WHERE QUESTION_ID = #{questionId} AND USER_ID = #{userId};
    """)
    QuestionInfoDto findQuestionById(@Param("questionId") int questionId, @Param("userId") int userId);

    @Update("""
    UPDATE QUESTIONS 
    SET QUESTION_CONTENT = #{question.questionContent}, 
        EXPLANATION = #{question.explanation}, 
        UPDATED_DATETIME = CURRENT_TIMESTAMP
    WHERE QUESTION_ID = #{question.questionId}
    """)
    void updateQuestion(@Param("question") Question question);

    @Update("""
    UPDATE QUESTIONS 
    SET QUESTION_CONTENT = #{questionContent}, 
        EXPLANATION = #{explanation}, 
        UPDATED_DATETIME = CURRENT_TIMESTAMP
    WHERE QUESTION_ID = #{questionId}
    """)
    void updateQuestionInfo(@Param("questionId") int questionId, @Param("questionContent") String questionContent, @Param("explanation") String explanation);


}
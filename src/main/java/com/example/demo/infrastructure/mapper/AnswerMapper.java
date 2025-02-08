package com.example.demo.infrastructure.mapper;
import com.example.demo.domain.entity.Choice;
import com.example.demo.infrastructure.dto.AnswerDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AnswerMapper {

    @Select("""
    SELECT COALESCE(MAX(ANSWER_ID), 0) FROM ANSWERS
    """)
    int getNextId();
    
    @Insert("""
    INSERT INTO ANSWERS (ANSWER_ID, QUESTION_ID, ANSWER_CONTENT, CREATED_DATETIME, UPDATED_DATETIME) VALUES (#{answerId}, #{questionId}, #{correctAnswer}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
    )
    """)
    void insertAnswer(@Param("answerId") int answerId, @Param("questionId")int questionId, @Param("correctAnswer") int correctAnswer);

    @Select("""
    SELECT ANSWER_ID, ANSWER_CONTENT FROM ANSWERS WHERE QUESTION_ID = #{questionId}
    """)
    AnswerDto findAnswerByQuestionId(@Param("questionId") int questionId);

    @Update("""
    UPDATE ANSWERS SET ANSWER_CONTENT = #{correctAnswer}, UPDATED_DATETIME = CURRENT_TIMESTAMP
    WHERE ANSWER_ID = #{answerId} AND QUESTION_ID = #{questionId}
""")
    void updateAnswer(@Param("answerId") int answerId, @Param("questionId")int questionId, @Param("correctAnswer") int correctAnswer);

}
package com.example.demo.infrastructure.mapper;
import com.example.demo.domain.entity.Choice;
import com.example.demo.infrastructure.dto.ChoiceDto;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Select("""
    SELECT CHOICE_ID, CHOICE_CONTENT FROM CHOICES WHERE QUESTION_ID = #{questionId} ORDER BY CHOICE_ID;
    """)
    List<ChoiceDto> searchChoicesByQuestionId(@Param("questionId") int questionId);

    @Update("""
        UPDATE CHOICES SET CHOICE_CONTENT = #{choice.choiceContent}, UPDATED_DATETIME = CURRENT_TIMESTAMP
        WHERE CHOICE_ID = #{choice.choiceId} AND QUESTION_ID = #{questionId}
    """)
    void updateChoice(@Param("choice") ChoiceDto choice, @Param("questionId") int questionId);

    @Delete("""
    DELETE FROM CHOICES WHERE CHOICE_ID = #{choiceId}
    """)
    void deleteChoice(@Param("choiceId") int choiceId);
}
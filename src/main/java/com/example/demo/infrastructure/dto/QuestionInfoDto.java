package com.example.demo.infrastructure.dto;
import lombok.Data;
@Data
public class QuestionInfoDto {
    private int questionId;
    private String questionContent;
    private String explanation;

    public QuestionInfoDto(int questionId, String questionContent, String explanation) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.explanation = explanation;
    }

    public int getQuestionId() {
        return this.questionId;
    }

    public String getQuestionContent() {
        return this.questionContent;
    }
    
    public String getExplanation() {
        return this.explanation;
    }

}
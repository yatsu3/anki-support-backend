package com.example.demo.infrastructure.dto;
import lombok.Data;
@Data
public class QuestionDto {
    private int questionId;
    private String questionContent;

    public QuestionDto(int questionId, String questionContent) {
        this.questionId = questionId;
        this.questionContent = questionContent;
    }

    public int getQuestionId() {
        return this.questionId;
    }

    public String getQuestionContent() {
        return this.questionContent;
    }

}
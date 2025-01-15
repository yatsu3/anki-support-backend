package com.example.demo.domain.entity;
import lombok.Data;

@Data
public class Question {

    private int questionId;
    private int userId;
    private int categoryId;
    private String questionContent;
    private String explanation;
    private int correctedAnswer;

    public Question(int questionId, int userId, int categoryId, String questionContent, String explanation, int correctedAnswer) {
        this.questionId = questionId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.questionContent = questionContent;
        this.explanation = explanation;
        this.correctedAnswer = correctedAnswer;
    }
}
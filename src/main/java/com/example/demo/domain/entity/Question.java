package com.example.demo.domain.entity;
import lombok.Data;

@Data
public class Question {

    private int questionId;
    private int userId;
    private int categoryId;
    private int answerId;
    private String questionContent;
    private String explanation;

    public Question(int questionId, int userId, int categoryId, int answerId, String questionContent, String explanation) {
        this.questionId = questionId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.answerId = answerId;
        this.questionContent = questionContent;
        this.explanation = explanation;
    }
}
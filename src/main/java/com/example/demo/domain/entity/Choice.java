package com.example.demo.domain.entity;
import lombok.Data;

@Data
public class Choice {

    private int choiceId;
    private int questionId;
    private String choiceContent;

    public Choice(int choiceId, int questionId, String choiceContent) {
        this.choiceId = choiceId;
        this.questionId = questionId;
        this.choiceContent = choiceContent;
    }
}
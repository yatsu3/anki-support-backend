package com.example.demo.infrastructure.dto;
import lombok.Data;
@Data
public class AnswerDto {
    private int answerId;
    private int answerContent;

    public AnswerDto(int answerId, int answerContent) {
        this.answerId = answerId;
        this.answerContent = answerContent;
    }

    public int getAnswerId() {
        return this.answerId;
    }

    public int getAnswerContent() {
        return this.answerContent;
    }

}
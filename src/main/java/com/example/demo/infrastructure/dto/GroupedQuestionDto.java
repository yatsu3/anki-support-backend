package com.example.demo.infrastructure.dto;
import lombok.Data;
@Data
public class GroupedQuestionDto {
    private int questionId;
    private String questionContent;
    private String choiceContent;
    private int correctedAnswer;
    private String explanation;

    public GroupedQuestionDto(int questionId, String questionContent, String choiceContent, int correctedAnswer, String explanation) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.choiceContent = choiceContent;
        this.correctedAnswer = correctedAnswer;
        this.explanation = explanation;
    }

    public int getQuestionId() {
        return this.questionId;
    }

    public String getQuestionContent() {
        return this.questionContent;
    }

    public String getChoiceContent() {
        return this.choiceContent;
    }

    public int getCorrectedAnswer() {
        return this.correctedAnswer;
    }
    
    public String getExplanation() {
        return this.explanation;
    }

}
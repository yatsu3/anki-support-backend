package com.example.demo.domain.entity;
import java.util.List;
import lombok.Data;
@Data
public class GroupedQuestion {
    private int questionId;
    private String questionContent;
    List<String> choiceContents;
    int correctedAnswer;
    String explanation;

    public GroupedQuestion(int questionId, String questionContent, List<String> choiceContents, int correctedAnswer, String explanation) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.choiceContents = choiceContents;
        this.correctedAnswer = correctedAnswer;
        this.explanation = explanation;
    }

}
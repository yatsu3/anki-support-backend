package com.example.demo.presentation;
import java.util.List;
import lombok.Data;

@Data
public class QuestionRequest {

    private String categoryName;
    private int userId;
    private String questionContent;
    private List<String> choicesData;
    private int correctAnswer;
    private String explanation;

    public QuestionRequest(String categoryName, int userId, String questionContent, List<String> choicesData, int correctAnswer, String explanation) {
        this.categoryName = categoryName;
        this.userId = userId;
        this.questionContent = questionContent;
        this.choicesData = choicesData;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getQuestionContent() {
        return this.questionContent;
    }

    public List<String> getChoicesData() {
        return this.choicesData;
    }

    public int getAnswer() {
        return this.correctAnswer;
    }

    public String getExplanation() {
        return this.explanation;
    }

}
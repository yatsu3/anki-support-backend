package com.example.demo.presentation;
import com.example.demo.infrastructure.dto.ChoiceDto;
import java.util.List;
import lombok.Data;

@Data
public class UpdateQuestionRequest {

    private int questionId;
    private String categoryName;
    private String uuid;
    private String questionContent;
    private List<ChoiceDto> choicesData;
    private int correctAnswer;
    private String explanation;

    public UpdateQuestionRequest(int questionId,String categoryName, String uuid, String questionContent, List<ChoiceDto> choicesData, int correctAnswer, String explanation) {
        this.questionId = questionId;
        this.categoryName = categoryName;
        this.uuid = uuid;
        this.questionContent = questionContent;
        this.choicesData = choicesData;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
    }

    public int getQuestionId() {
        return this.questionId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getQuestionContent() {
        return this.questionContent;
    }

    public List<ChoiceDto> getChoicesData() {
        return this.choicesData;
    }

    public int getAnswer() {
        return this.correctAnswer;
    }

    public String getExplanation() {
        return this.explanation;
    }

}
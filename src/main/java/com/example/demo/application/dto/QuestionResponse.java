package com.example.app.application.dto;
import com.example.demo.domain.entity.Choice;
import com.example.demo.infrastructure.dto.ChoiceDto;
import java.util.List;

public class QuestionResponse {
    private int questionId;
    private String categoryName;
    private String questionContent;
    private List<ChoiceDto> choiceList;
    private int answerContent;
    private String explanation;


    public QuestionResponse(int questionId, String categoryName, String questionContent, List<ChoiceDto> choiceList, int answerContent, String explanation) {
        this.questionId = questionId;
        this.categoryName = categoryName;
        this.questionContent = questionContent;
        this.choiceList = choiceList;
        this.answerContent = answerContent;
        this.explanation = explanation;
    }

    public int getQuestionId() { return this.questionId; }
    public String getCategoryName() { return this.categoryName; }
    public String getQuestionContent() { return this.questionContent; }
    public List<ChoiceDto> getChoiceList() { return this.choiceList; }
    public int getAnswerContent() { return this.answerContent; }
    public String getExplanation() { return this.explanation; }
}

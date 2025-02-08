package com.example.demo.infrastructure.dto;
import lombok.Data;
@Data
public class ChoiceDto {
    private int choiceId;
    private String choiceContent;

    public ChoiceDto(int choiceId, String choiceContent) {
        this.choiceId = choiceId;
        this.choiceContent = choiceContent;
    }

    public int getChoiceId() {
        return this.choiceId
;
    }

    public String getChoiceContent() {
        return this.choiceContent;
    }
    
}
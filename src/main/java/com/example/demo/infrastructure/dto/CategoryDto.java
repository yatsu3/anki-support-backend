package com.example.demo.infrastructure.dto;
import lombok.Data;
@Data
public class CategoryDto {
    private int categoryId;
    private String categoryName;
    private int questionCount;

    public CategoryDto(int categoryId, String categoryName, int questionCount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.questionCount = questionCount;
    }

}
package com.example.demo.infrastructure.dto;
import lombok.Data;
@Data
public class CategoryInfoDto {
    private int categoryId;
    private String categoryName;

    public CategoryInfoDto(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

}
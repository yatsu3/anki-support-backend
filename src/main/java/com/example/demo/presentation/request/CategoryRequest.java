package com.example.demo.presentation;
import lombok.Data;

@Data
public class CategoryRequest {
    
    private String categoryName;

    private int userId;

    public CategoryRequest(String categoryName, int userId) {
        this.categoryName = categoryName;
        this.userId = userId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public int getUserId() {
        return this.userId;
    }


}
package com.example.demo.presentation;
import lombok.Data;

@Data
public class CategoryRequest {
    
    private String categoryName;

    private String uuid;

    public CategoryRequest(String categoryName, String uuid) {
        this.categoryName = categoryName;
        this.uuid = uuid;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public String getUuId() {
        return this.uuid;
    }


}
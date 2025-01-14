package com.example.demo.domain.entity;
import java.util.Objects;
import lombok.Data;

@Data
public class Category {

    private int categoryId;
    private int userId;
    private String categoryName;

    public Category(int categoryId, int userId, String categoryName) {
        this.categoryId = categoryId;
        this.userId = userId;
        if (Objects.isNull(categoryName)) {
            throw new IllegalArgumentException("カテゴリー名は必須です。");
        }
        this.categoryName = categoryName;
    }
}
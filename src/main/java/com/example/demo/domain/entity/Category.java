package com.example.demo.domain.entity;
import java.util.Objects;
import lombok.Data;

@Data
public class Category {

    private int categoryId;
    private String categoryName;

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        if (Objects.isNull(categoryName)) {
            throw new IllegalArgumentException("カテゴリー名は必須です。");
        }
        this.categoryName = categoryName;
    }
}
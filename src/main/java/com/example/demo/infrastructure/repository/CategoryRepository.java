package com.example.demo.infrastructure.repository;
import com.example.demo.domain.entity.Category;
import com.example.demo.infrastructure.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class CategoryRepository {

    @Autowired
    CategoryMapper mapper;

    public int getNextId() {
        return mapper.getNextId() + 1;
    }

    public int getIdByCategoryName(String name) {
        return mapper.getIdByCategoryName(name);
    }

    public void registerCategory(Category category) {
        mapper.insertCategory(category);
    }

}
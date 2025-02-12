package com.example.demo.infrastructure.repository;
import com.example.demo.domain.entity.Category;
import com.example.demo.infrastructure.dto.CategoryDto;
import com.example.demo.infrastructure.dto.CategoryInfoDto;
import com.example.demo.infrastructure.mapper.CategoryMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class CategoryRepository {

    @Autowired
    CategoryMapper mapper;

    public int getNextId(int userId) {
        return mapper.getNextId(userId) + 1;
    }

    public int getIdByCategoryName(String name, int userId) {
        return mapper.getIdByCategoryName(name, userId);
    }

    public void registerCategory(Category category) {
        mapper.insertCategory(category);
    }

    public List<CategoryDto> getCategory(int userId) {
        return mapper.getCategory(userId);
    }

    public CategoryInfoDto findCategoryById(int questionId) {
        return mapper.findCategoryById(questionId);
    }

}
package com.example.demo.domain.service;
import com.example.demo.domain.entity.Category;
import com.example.demo.infrastructure.dto.CategoryDto;
import com.example.demo.infrastructure.dto.CategoryInfoDto;
import com.example.demo.infrastructure.repository.CategoryRepository;
import com.example.demo.presentation.CategoryRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;
    
    public void registerCategory(CategoryRequest request) {

        int id = repository.getNextId(request.getUserId());
        Category category = new Category(id, request.getUserId(), request.getCategoryName());

        repository.registerCategory(category);

    }

    public List<CategoryDto> getCategory(int userId) {
        return repository.getCategory(userId);
    }

    public CategoryInfoDto findCategoryById(int questionId) {
        return repository.findCategoryById(questionId);
    }
}
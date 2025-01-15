package com.example.demo.domain.service;
import com.example.demo.domain.entity.Category;
import com.example.demo.infrastructure.dto.CategoryDto;
import com.example.demo.infrastructure.repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CategoryService {

    @Autowired
    CategoryRepository repository;
    
    public void registerCategory(String categoryName, int userId) {

        int id = repository.getNextId();
        Category category = new Category(id, userId, categoryName);

        repository.registerCategory(category);

    }

    public List<CategoryDto> getCategory(int userId) {
        return repository.getCategory(userId);
     }
}
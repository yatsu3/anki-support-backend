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

    @Autowired
    UserService userService;
    
    public void registerCategory(CategoryRequest request) {
        int userId = userService.getUserIdByUuid(request.getUuId());
        int id = repository.getNextId(userId);
        Category category = new Category(id, userId, request.getCategoryName());

        repository.registerCategory(category);

    }

    public List<CategoryDto> getCategory(String uuid) {
        // uuidからuserIdを取得
        int userId = userService.getUserIdByUuid(uuid);
        return repository.getCategory(userId);
    }

    public CategoryInfoDto findCategoryById(int questionId) {
        return repository.findCategoryById(questionId);
    }
}
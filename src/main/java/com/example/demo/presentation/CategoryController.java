package com.example.demo.presentation;
import com.example.demo.domain.service.CategoryService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class CategoryController {
    @Autowired
    CategoryService service;

    @PostMapping(value="/add-category")
    public void addCategory(@RequestBody Map<String, Object> requestBody) {
        String categoryName = (String)requestBody.get("title");
        int userId = (int)requestBody.get("userId");

        
        service.registerCategory(categoryName, userId); 
    }
}
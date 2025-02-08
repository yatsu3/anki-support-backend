package com.example.demo.presentation;
import com.example.demo.domain.service.CategoryService;
import com.example.demo.infrastructure.dto.CategoryDto;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class CategoryController {
    @Autowired
    CategoryService service;

    @PostMapping(value="/add-category")
    public void addCategory(@RequestBody CategoryRequest request) {

        service.registerCategory(request); 
    }

    @GetMapping("/get-category")
    public List<CategoryDto> getCategory(@RequestParam int userId) {
        return service.getCategory(userId);
    }
}
package com.example.demo.domain.service;
import com.example.demo.domain.entity.Category;
import com.example.demo.infrastructure.dto.CategoryDto;
import com.example.demo.infrastructure.dto.CategoryInfoDto;
import com.example.demo.infrastructure.repository.CategoryRepository;
import com.example.demo.infrastructure.repository.UserRepository;
import com.example.demo.presentation.CategoryRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public int getUserIdByUuid(String uuid) {
        return repository.getUserIdByUuid(uuid);
    }
    
}
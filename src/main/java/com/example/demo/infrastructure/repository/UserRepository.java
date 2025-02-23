package com.example.demo.infrastructure.repository;
import com.example.demo.domain.entity.Category;
import com.example.demo.infrastructure.dto.CategoryDto;
import com.example.demo.infrastructure.dto.CategoryInfoDto;
import com.example.demo.infrastructure.mapper.CategoryMapper;
import com.example.demo.infrastructure.mapper.UserMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class UserRepository {

    @Autowired
    UserMapper mapper;

    public int getUserIdByUuid(String uuid) {
        return mapper.getUserIdByUuid(uuid);
    }

}
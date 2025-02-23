package com.example.demo.infrastructure.mapper;
import com.example.demo.domain.entity.Category;
import com.example.demo.infrastructure.dto.CategoryDto;
import com.example.demo.infrastructure.dto.CategoryInfoDto;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("""
    SELECT USER_ID FROM USERS WHERE UUID = #{uuid}
    """)
    int getUserIdByUuid(String uuid);
    
}
package com.example.demo.infrastructure.mapper;
import com.example.demo.domain.entity.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {

    @Select("""
    SELECT COALESCE(MAX(CATEGORY_ID), 0) FROM CATEGORIES
    """)
    int getNextId();
    
    @Insert("""
    INSERT INTO CATEGORIES (CATEGORY_ID, CATEGORY_NAME, CREATED_DATETIME, UPDATED_DATETIME) VALUES (#{category.categoryId}, #{category.categoryName}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
    )
    """)
    void insertCategory(@Param("category") Category category);
}
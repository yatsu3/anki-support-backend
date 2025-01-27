package com.example.demo.infrastructure.mapper;
import com.example.demo.domain.entity.Category;
import com.example.demo.infrastructure.dto.CategoryDto;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {

    @Select("""
    SELECT COALESCE(MAX(CATEGORY_ID), 0) FROM CATEGORIES WHERE USER_ID = #{userId}
    """)
    int getNextId(int userId);

    @Select("""
    SELECT CATEGORY_ID FROM CATEGORIES WHERE CATEGORY_NAME = #{categoryName}
    """)
    int getIdByCategoryName(String categoryName);
    
    @Insert("""
    INSERT INTO CATEGORIES (CATEGORY_ID, USER_ID, CATEGORY_NAME, CREATED_DATETIME, UPDATED_DATETIME) VALUES (#{category.categoryId}, #{category.userId}, #{category.categoryName}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
    )
    """)
    void insertCategory(@Param("category") Category category);

    @Select("""
    SELECT 
    c.CATEGORY_ID,
    c.CATEGORY_NAME, 
    COUNT(q.QUESTION_ID)
    FROM 
        CATEGORIES c
    LEFT JOIN 
        QUESTIONS q
    ON 
        c.CATEGORY_ID = q.CATEGORY_ID
    WHERE 
        c.USER_ID = #{userId}
    GROUP BY 
        c.CATEGORY_ID;
    """)
    List<CategoryDto> getCategory(@Param("userId")int userId);
}
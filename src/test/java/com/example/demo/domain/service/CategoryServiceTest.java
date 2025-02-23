package com.example.demo.domain.service;

import com.example.demo.domain.entity.Category;
import com.example.demo.infrastructure.dto.CategoryDto;
import com.example.demo.infrastructure.repository.CategoryRepository;
import com.example.demo.presentation.CategoryRequest;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository; // リポジトリをモック化

    @Mock
    private UserService userService; // UserServiceをモック化

    @InjectMocks
    private CategoryService categoryService; // テスト対象のサービス

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterCategory() {
        // モックの動作を定義
        when(categoryRepository.getNextId(1)).thenReturn(1);

        CategoryRequest request = new CategoryRequest("Test Category", 1);

        // メソッドを実行
        categoryService.registerCategory(request);

        // 引数キャプチャを使って、Category オブジェクトが正しく作成されるか検証
        ArgumentCaptor<Category> categoryCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository, times(1)).registerCategory(categoryCaptor.capture());

        Category capturedCategory = categoryCaptor.getValue();
        assertEquals(1, capturedCategory.getCategoryId()); // ID が正しく設定されたか
        assertEquals(1, capturedCategory.getUserId()); // ユーザーIDが正しく設定されたか
        assertEquals("Test Category", capturedCategory.getCategoryName()); // カテゴリ名が正しく設定されたか
    }

    @Test
    void カテゴリー情報が取得できること() {
        // モックの動作を定義
        List<CategoryDto> mockCategories = Arrays.asList(
            new CategoryDto(1, "Category 1", 1),
            new CategoryDto(2, "Category 2", 1)
        );
        when(categoryRepository.getCategory(100)).thenReturn(mockCategories);

        when(userService.getUserIdByUuid("100")).thenReturn(100);

        // メソッドを実行
        List<CategoryDto> result = categoryService.getCategory("100");

        // 取得データが期待通りか検証
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Category 1", result.get(0).getCategoryName());
        assertEquals("Category 2", result.get(1).getCategoryName());

        // メソッドが呼ばれていることの確認
        verify(categoryRepository, times(1)).getCategory(100);
    }
}

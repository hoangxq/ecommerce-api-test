package com.demo.service;

import com.demo.web.dto.request.CategoryRequest;
import com.demo.web.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategory();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse createCategory(CategoryRequest categoryReq);
    CategoryResponse updateCategory(CategoryRequest categoryRequest, Long id);
    void deleteCategoryById(Long id);
}

package com.satyam.service;

import java.util.List;

import com.satyam.dto.CategoryDto;
import com.satyam.utils.CategoryResponse;

public interface ICategoryService {
    CategoryResponse getCategoryById(Integer id);

    List<CategoryResponse> getAllCategory();

    CategoryResponse deleteCategoryById(Integer id);

    CategoryResponse updateCategory(CategoryDto categoryDto);

    CategoryResponse createCategory(CategoryDto categoryDto);
}

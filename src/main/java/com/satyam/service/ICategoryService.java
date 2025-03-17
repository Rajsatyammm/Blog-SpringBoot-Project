package com.satyam.service;

import com.satyam.dto.CategoryDto;
import com.satyam.utils.ApiResponse;

public interface ICategoryService {
    ApiResponse getCategoryById(Integer id);

    ApiResponse getAllCategory();

    ApiResponse deleteCategoryById(Integer id);

    ApiResponse updateCategory(CategoryDto categoryDto);

    ApiResponse createCategory(CategoryDto categoryDto);
}

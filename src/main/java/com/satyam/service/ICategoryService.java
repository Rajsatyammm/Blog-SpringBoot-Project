package com.satyam.service;

import java.util.List;

import com.satyam.dto.CategoryDto;

public interface ICategoryService {
    CategoryDto getCategoryById(Integer id);

    List<CategoryDto> getAllCategory();

    String deleteCategoryById(Integer id);

    CategoryDto updateCategory(CategoryDto categoryDto);

    String createCategory(CategoryDto categoryDto);
}

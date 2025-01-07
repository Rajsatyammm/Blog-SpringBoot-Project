package com.satyam.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.satyam.dto.CategoryDto;
import com.satyam.exceptions.CustomException;
import com.satyam.model.Category;
import com.satyam.repository.ICategoryRepository;
import com.satyam.service.ICategoryService;
import com.satyam.utils.CategoryResponse;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException("Category not found with id " + id, HttpStatus.NOT_FOUND, false));
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        return categoryRepository
                .findAll()
                .stream()
                .map((category) -> modelMapper.map(category, CategoryResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse deleteCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException("Category not found with id " + id, HttpStatus.NOT_FOUND, false));
        categoryRepository.delete(category);
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse updateCategory(CategoryDto categoryDto) {
        categoryRepository.findById(categoryDto.getCategoryId())
                .orElseThrow(() -> new CustomException("Category not found with id " + categoryDto.getCategoryId(),
                        HttpStatus.NOT_FOUND, false));
        Category updatedCategory = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        return modelMapper.map(updatedCategory, CategoryResponse.class);
    }

    @Override
    public CategoryResponse createCategory(CategoryDto categoryDto) {
        Category savedCategory = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        return modelMapper.map(savedCategory, CategoryResponse.class);
    }
}

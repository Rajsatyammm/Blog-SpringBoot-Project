package com.satyam.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent())
            return modelMapper.map(optional.get(), CategoryResponse.class);
        throw new CustomException("Category not found by Id " + id, "404", false);
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
                .orElseThrow(() -> new CustomException("Category not found with id " + id, "404", false));
        categoryRepository.delete(category);
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public CategoryResponse updateCategory(CategoryDto categoryDto) {
        categoryRepository.findById(categoryDto.getCategoryId())
                .orElseThrow(() -> new CustomException("Category not found with id " + categoryDto.getCategoryId(),
                        "404", false));
        Category updatedCategory = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        return modelMapper.map(updatedCategory, CategoryResponse.class);
    }

    @Override
    public CategoryResponse createCategory(CategoryDto categoryDto) {
        Category savedCategory = categoryRepository.save(modelMapper.map(categoryDto, Category.class));
        return modelMapper.map(savedCategory, CategoryResponse.class);
    }
}

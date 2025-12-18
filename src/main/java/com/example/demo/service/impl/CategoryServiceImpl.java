package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }
    public Category getCategory(Long id){
        Optional<Category>opcategory= categoryRepository.findById(id);
        return opcategory.orElse(null);
    }
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}

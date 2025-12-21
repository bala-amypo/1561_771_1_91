package com.example.demo.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @PostMapping
    public ResponseEntity<Category> createData(@RequestBody Category category){
        return ResponseEntity.status(201).body(categoryService.createCategory(category));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id){
        return ResponseEntity.status(200).body(categoryService.getCategory(id)) ;
    }
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categorydata= categoryService.getAllCategories();
        return ResponseEntity.ok(categorydata);
    }
}

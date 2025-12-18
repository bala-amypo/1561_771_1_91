package com.example.demo.service.impl;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Category;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategorizationRuleService;

@Service
public class CategorizationRuleServiceImpl implements CategorizationRuleService{
    private final CategorizationRuleRepository categorizationRuleRepository;
    private final CategoryRepository categoryRepository;
     CategorizationRuleServiceImpl(CategorizationRuleRepository categorizationRuleRepository,CategoryRepository categoryRepository){
        this.categorizationRuleRepository=categorizationRuleRepository;
        this.categoryRepository=categoryRepository;

    }
    @Override
    public CategorizationRule createRule(Long categoryId,CategorizationRule rule){
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category not found"));
        rule.setCategory(category);

        return categorizationRuleRepository.save(rule);
        
    }

    @Override
    public List<CategorizationRule>getRulesByCategory(Long categoryId){
        return categorizationRuleRepository.findByCategoryId(categoryId);
    }
    @Override
    public CategorizationRule getPolicy(Long id){
        return categorizationRuleRepository.findById(id).orElseThrow(() -> new RuntimeException("Rule not found"));
    }

}

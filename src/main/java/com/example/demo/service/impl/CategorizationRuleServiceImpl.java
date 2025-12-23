package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CategorizationRule;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.service.CategorizationRuleService;

@Service
public class CategorizationRuleServiceImpl 
        implements CategorizationRuleService {

    private final CategorizationRuleRepository categorizationRuleRepository;

    public CategorizationRuleServiceImpl(
            CategorizationRuleRepository categorizationRuleRepository) {
        this.categorizationRuleRepository = categorizationRuleRepository;
    }

    @Override
    public CategorizationRule createRule(CategorizationRule rule) {
        return categorizationRuleRepository.save(rule);
    }

    @Override
    public CategorizationRule getRule(Long id) {
        return categorizationRuleRepository.findById(id)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Rule not found"));
    }

    @Override
    public List<CategorizationRule> getAllRules() {
        return categorizationRuleRepository.findAll();
    }

    @Override
    public List<CategorizationRule> getRulesByCategory(Long categoryId) {
        return categorizationRuleRepository.findByCategory_Id(categoryId);
    }

    @Override
    public void deleteRule(Long id) {
        CategorizationRule rule = getRule(id);
        categorizationRuleRepository.delete(rule);
    }
}

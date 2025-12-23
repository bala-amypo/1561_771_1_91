package com.example.demo.service;

import java.util.List;
import com.example.demo.model.CategorizationRule;

public interface CategorizationRuleService {

    CategorizationRule createRule(Long categoryId, CategorizationRule rule);

    CategorizationRule getRule(Long id);

    List<CategorizationRule> getAllRules();

    List<CategorizationRule> getRulesByCategory(Long categoryId);

    void deleteRule(Long id);
}

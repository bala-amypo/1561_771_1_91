package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CategorizationRule;
import com.example.demo.service.CategorizationRuleService;

@RestController
@RequestMapping("/api/rules")
public class CategorizationRuleController {
   
    private final CategorizationRuleService categorizationRuleService;
    CategorizationRuleController(CategorizationRuleService categorizationRuleService){
        this.categorizationRuleService=categorizationRuleService;
    }
    @PostMapping("/{categoryId}")
    public CategorizationRule createData(@PathVariable Long categoryId,@RequestBody CategorizationRule rule){
        return categorizationRuleService.createRule(categoryId, rule);
    }
    @GetMapping("/{id}")
    public CategorizationRule getdata(@PathVariable Long id){
        return categorizationRuleService.getPolicy(id);
    }
    @GetMapping("/category/{categoryId}")
    public List<CategorizationRule>getRulesByCategory(Long categoryId){

        return categorizationRuleService.getRulesByCategory(categoryId);
    }

}



package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<CategorizationRule> createData(@PathVariable Long categoryId,@RequestBody CategorizationRule rule){
        return ResponseEntity.status(201).body(categorizationRuleService.createRule(categoryId, rule));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategorizationRule> getdata(@PathVariable Long id){
        return ResponseEntity.status(200).body(categorizationRuleService.getRule(id));
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<CategorizationRule>>getRulesByCategory(@PathVariable Long categoryId){

        List<CategorizationRule> data =  categorizationRuleService.getRulesByCategory(categoryId);
        return ResponseEntity.status(200).body(data);
    }
    
   

}



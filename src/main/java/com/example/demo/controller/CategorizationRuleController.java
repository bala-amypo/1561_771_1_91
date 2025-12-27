package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.CategorizationRule;
import com.example.demo.service.CategorizationRuleService;

@RestController
@RequestMapping("/api/rules")
public class CategorizationRuleController {

    private final CategorizationRuleService ruleService;

    public CategorizationRuleController(CategorizationRuleService ruleService) {
        this.ruleService = ruleService;
    }

    // ✅ ADMIN only – create rule under a category
    @PostMapping("/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategorizationRule> createRule(
            @PathVariable Long categoryId,
            @RequestBody CategorizationRule rule) {

        return ResponseEntity.ok(
                ruleService.createRule(categoryId, rule)
        );
    }

    // ✅ ADMIN only – get rules by category
    @GetMapping("/category/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CategorizationRule>> getByCategory(
            @PathVariable Long categoryId) {

        return ResponseEntity.ok(
                ruleService.getRulesByCategory(categoryId)
        );
    }

    // ✅ ADMIN only – get rule by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategorizationRule> getRule(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getRule(id));
    }
}

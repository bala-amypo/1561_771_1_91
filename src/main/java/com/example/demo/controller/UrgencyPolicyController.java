package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.UrgencyPolicy;
import com.example.demo.service.UrgencyPolicyService;

@RestController
@RequestMapping("/api/policies")
public class UrgencyPolicyController {

    private final UrgencyPolicyService policyService;

    public UrgencyPolicyController(UrgencyPolicyService policyService) {
        this.policyService = policyService;
    }

    // ✅ ADMIN only – create urgency policy
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UrgencyPolicy> create(@RequestBody UrgencyPolicy policy) {
        return ResponseEntity.ok(policyService.createPolicy(policy));
    }

    // ✅ ADMIN only – view all policies
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UrgencyPolicy>> getAll() {
        return ResponseEntity.ok(policyService.getAllPolicies());
    }

    // ✅ ADMIN only – view policy by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UrgencyPolicy> getById(@PathVariable Long id) {
        return ResponseEntity.ok(policyService.getPolicy(id));
    }
}

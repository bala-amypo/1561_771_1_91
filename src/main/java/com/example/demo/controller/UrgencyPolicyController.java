package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UrgencyPolicy;
import com.example.demo.service.UrgencyPolicyService;

@RestController
@RequestMapping("/api/policies")
public class UrgencyPolicyController {
    private final UrgencyPolicyService urgencyPolicyService;
    UrgencyPolicyController(UrgencyPolicyService urgencyPolicyService){
        this.urgencyPolicyService=urgencyPolicyService;
    }
    @PostMapping
    public ResponseEntity<UrgencyPolicy> createPolicy(@RequestBody UrgencyPolicy urgencyPolicy){
        return ResponseEntity.status(201).body(urgencyPolicyService.createPolicy(urgencyPolicy));
    }
    @GetMapping
    public ResponseEntity<List<UrgencyPolicy>> getAllPolicies(){
        return ResponseEntity.status(200).body(urgencyPolicyService.getAllPolicies());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UrgencyPolicy> getPolicy(@PathVariable Long id){
        return ResponseEntity.status(200).body(urgencyPolicyService.getPolicy(id));
    }


}

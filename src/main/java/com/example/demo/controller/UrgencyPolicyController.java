package com.example.demo.controller;

import java.util.List;

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
    public UrgencyPolicy createPolicy(@RequestBody UrgencyPolicy urgencyPolicy){
        return urgencyPolicyService.createPolicy(urgencyPolicy);
    }
    @GetMapping
    public List<UrgencyPolicy> getAllPolicies(){
        return urgencyPolicyService.getAllPolicies();
    }
    @GetMapping("/{id}")
    public UrgencyPolicy getPolicy(@PathVariable Long id){
        return urgencyPolicyService.getPolicy(id);
    }


}

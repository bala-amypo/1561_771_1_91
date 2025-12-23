package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UrgencyPolicy;
import com.example.demo.repository.UrgencyPolicyRepository;
import com.example.demo.service.UrgencyPolicyService;

@Service
public class UrgencyPolicyServiceImpl implements UrgencyPolicyService {

    private final UrgencyPolicyRepository urgencyPolicyRepository;

    public UrgencyPolicyServiceImpl(UrgencyPolicyRepository urgencyPolicyRepository) {
        this.urgencyPolicyRepository = urgencyPolicyRepository;
    }

    @Override
    public UrgencyPolicy createPolicy(UrgencyPolicy policy) {
        return urgencyPolicyRepository.save(policy);
    }

    @Override
    public UrgencyPolicy getPolicy(Long id) {
        return urgencyPolicyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));
    }

    @Override
    public List<UrgencyPolicy> getAllPolicies() {
        return urgencyPolicyRepository.findAll();
    }
}

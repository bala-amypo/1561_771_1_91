package com.example.demo.service;

import java.util.List;

import com.example.demo.model.UrgencyPolicy;

public interface UrgencyPolicyService {

    UrgencyPolicy createPolicy(UrgencyPolicy policy);

    UrgencyPolicy getPolicy(Long id);

    List<UrgencyPolicy> getAllPolicies();
}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UrgencyPolicy;

public interface UrgencyPolicyRepository
        extends JpaRepository<UrgencyPolicy, Long> {

    List<UrgencyPolicy> findByKeywordContainingIgnoreCase(String keyword);
}

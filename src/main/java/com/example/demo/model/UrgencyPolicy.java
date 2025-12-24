package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class UrgencyPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyName;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private String urgencyOverride;

    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "urgencyPolicies")
    private Set<Category> categories = new HashSet<>();

    // ✅ No-args constructor
    public UrgencyPolicy() {}

    // ✅ Auto timestamp
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // =========================
    // ✅ REQUIRED GETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public String getPolicyName() {
        return policyName;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getUrgencyOverride() {
        return urgencyOverride;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    // =========================
    // ✅ REQUIRED SETTERS
    // =========================

    public void setId(Long id) {
        this.id = id;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setUrgencyOverride(String urgencyOverride) {
        this.urgencyOverride = urgencyOverride;
    }
}

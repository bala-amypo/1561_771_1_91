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

    public UrgencyPolicy() {}

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // ✅ REQUIRED GETTERS (TESTS + ENGINE)
    public String getKeyword() {
        return keyword;
    }

    public String getUrgencyOverride() {
        return urgencyOverride;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    // Optional setters (safe to include)
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setUrgencyOverride(String urgencyOverride) {
        this.urgencyOverride = urgencyOverride;
    }
}

package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "urgency_policies")
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

    public UrgencyPolicy() {
    }

    public UrgencyPolicy(String policyName, String keyword, String urgencyOverride) {
        this.policyName = policyName;
        this.keyword = keyword;
        this.urgencyOverride = urgencyOverride;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUrgencyOverride() {
        return urgencyOverride;
    }

    public void setUrgencyOverride(String urgencyOverride) {
        this.urgencyOverride = urgencyOverride;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

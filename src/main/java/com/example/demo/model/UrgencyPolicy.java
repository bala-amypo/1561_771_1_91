package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "urgency_policies")
public class UrgencyPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyName;
    private String keyword;
    private String urgencyOverride;

    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
        name = "policy_categories",
        joinColumns = @JoinColumn(name = "policy_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    public UrgencyPolicy() {}

    public UrgencyPolicy(String policyName, String keyword, String urgencyOverride) {
        this.policyName = policyName;
        this.keyword = keyword;
        this.urgencyOverride = urgencyOverride;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPolicyName() { return policyName; }
    public void setPolicyName(String policyName) { this.policyName = policyName; }

    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }

    public String getUrgencyOverride() { return urgencyOverride; }
    public void setUrgencyOverride(String urgencyOverride) { this.urgencyOverride = urgencyOverride; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<Category> getCategories() { return categories; }
    public void setCategories(List<Category> categories) { this.categories = categories; }
}

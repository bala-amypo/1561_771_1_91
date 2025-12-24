package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String categoryName;

    private String description;

    @Column(nullable = false)
    private String defaultUrgency;

    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
        name = "category_urgency_policy",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "policy_id")
    )
    private Set<UrgencyPolicy> urgencyPolicies = new HashSet<>();

    public Category() {}

    public Category(String categoryName, String defaultUrgency) {
        this.categoryName = categoryName;
        this.defaultUrgency = defaultUrgency;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // ✅ prevents duplicates
    public void addUrgencyPolicy(UrgencyPolicy policy) {
        this.urgencyPolicies.add(policy);
        policy.getCategories().add(this);
    }

    // getters & setters (ALL required by tests)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getDefaultUrgency() { return defaultUrgency; }
    public void setDefaultUrgency(String defaultUrgency) { this.defaultUrgency = defaultUrgency; }

    public Set<UrgencyPolicy> getUrgencyPolicies() { return urgencyPolicies; }
}

package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    private Set<UrgencyPolicy> urgencyPolicies = new HashSet<>();

    // ✅ No-args constructor
    public Category() {}

    // ✅ Parameterized constructor
    public Category(String categoryName, String defaultUrgency) {
        this.categoryName = categoryName;
        this.defaultUrgency = defaultUrgency;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultUrgency() {
        return defaultUrgency;
    }

    public void setDefaultUrgency(String defaultUrgency) {
        this.defaultUrgency = defaultUrgency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Set<UrgencyPolicy> getUrgencyPolicies() {
        return urgencyPolicies;
    }
}

package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
@Entity
@Table(name="urgencypolicy")
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
    @ManyToMany
    private Set<Category> categories;
    @PrePersist
    protected void updated(){
        this.createdAt=LocalDateTime.now();
    }
    public UrgencyPolicy() {
    }
    
    public UrgencyPolicy(String policyName, String keyword, String urgencyOverride) {
        this.policyName = policyName;
        this.keyword = keyword;
        this.urgencyOverride = urgencyOverride;
        
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public Set<Category> getCategories() {
        return categories;
    }
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    
    

}

package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "assignedCategory")
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<CategorizationRule> rules = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "category_urgency_policy",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "policy_id")
    )
    private List<UrgencyPolicy> urgencyPolicies = new ArrayList<>();

    private LocalDateTime createdAt;

    /* =======================
       Constructors
       ======================= */

    public Category() {
    }

    public Category(String categoryName, String description, String defaultUrgency) {
        this.categoryName = categoryName;
        this.description = description;
        this.defaultUrgency = defaultUrgency;
    }

    /* =======================
       Lifecycle callback
       ======================= */

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    /* =======================
       Getters and Setters
       ======================= */

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

    // 🔥 REQUIRED BY ENGINE + TESTS
    public String getDefaultUrgency() {
        return defaultUrgency;
    }

    public void setDefaultUrgency(String defaultUrgency) {
        this.defaultUrgency = defaultUrgency;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<CategorizationRule> getRules() {
        return rules;
    }

    public void setRules(List<CategorizationRule> rules) {
        this.rules = rules;
    }

    // 🔥 REQUIRED BY TESTS
    public List<UrgencyPolicy> getUrgencyPolicies() {
        return urgencyPolicies;
    }

    public void setUrgencyPolicies(List<UrgencyPolicy> urgencyPolicies) {
        this.urgencyPolicies = urgencyPolicies;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

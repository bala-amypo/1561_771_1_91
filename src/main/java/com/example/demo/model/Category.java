package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String categoryName;

    private String description;
    private String defaultUrgency;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "assignedCategory")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "category")
    private List<CategorizationRule> rules;

    public Category() {}

    public Category(String categoryName, String description, String defaultUrgency) {
        this.categoryName = categoryName;
        this.description = description;
        this.defaultUrgency = defaultUrgency;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDefaultUrgency() { return defaultUrgency; }
    public void setDefaultUrgency(String defaultUrgency) { this.defaultUrgency = defaultUrgency; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

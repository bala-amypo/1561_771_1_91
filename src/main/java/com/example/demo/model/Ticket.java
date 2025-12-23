package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    private String location;

    private String createdBy;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category assignedCategory;

    @Column(nullable = false)
    private String urgencyLevel;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "ticket")
    private List<CategorizationLog> logs;

    public Ticket() {
    }

    public Ticket(String title, String description, String location, String createdBy) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.createdBy = createdBy;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.urgencyLevel == null) {
            this.urgencyLevel = "LOW";
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Category getAssignedCategory() {
        return assignedCategory;
    }

    public void setAssignedCategory(Category assignedCategory) {
        this.assignedCategory = assignedCategory;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

package com.example.demo.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Size;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    @Size(min = 10)
    private String description;
    private String location;
    private String createdBy;

    private String urgencyLevel;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn (name ="category_id",nullable =true)
    private Category assignedCategory;
    private LocalDateTime createdAt;
    @PrePersist
    protected void updated(){
        this.createdAt= LocalDateTime.now();
    }
    public Ticket() {
    }
    public Ticket(String title, @Size(min = 10) String description, String location, String createdBy,
            String urgencyLevel) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.createdBy = createdBy;
        this.urgencyLevel = urgencyLevel;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getUrgencyLevel() {
        return urgencyLevel;
    }
    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }
    public Category getAssignedCategory() {
        return assignedCategory;
    }
    public void setAssignedCategory(Category assignedCategory) {
        this.assignedCategory = assignedCategory;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}

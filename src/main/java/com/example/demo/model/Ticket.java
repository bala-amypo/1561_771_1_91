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

    private String title;
    private String description;
    private String location;
    private String createdBy;
    private String urgencyLevel;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category assignedCategory;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "ticket")
    private List<CategorizationLog> logs;

    public Ticket() {}

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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public String getUrgencyLevel() { return urgencyLevel; }
    public void setUrgencyLevel(String urgencyLevel) { this.urgencyLevel = urgencyLevel; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Category getAssignedCategory() { return assignedCategory; }
    public void setAssignedCategory(Category assignedCategory) { this.assignedCategory = assignedCategory; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<CategorizationLog> getLogs() { return logs; }
    public void setLogs(List<CategorizationLog> logs) { this.logs = logs; }
}

package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "categorization_logs")
public class CategorizationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matchedKeyword;

    private String appliedCategory;

    private String appliedUrgency;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    private LocalDateTime createdAt;

    public CategorizationLog() {
    }

    public CategorizationLog(String matchedKeyword, String appliedCategory, String appliedUrgency, Ticket ticket) {
        this.matchedKeyword = matchedKeyword;
        this.appliedCategory = appliedCategory;
        this.appliedUrgency = appliedUrgency;
        this.ticket = ticket;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getMatchedKeyword() {
        return matchedKeyword;
    }

    public void setMatchedKeyword(String matchedKeyword) {
        this.matchedKeyword = matchedKeyword;
    }

    public String getAppliedCategory() {
        return appliedCategory;
    }

    public void setAppliedCategory(String appliedCategory) {
        this.appliedCategory = appliedCategory;
    }

    public String getAppliedUrgency() {
        return appliedUrgency;
    }

    public void setAppliedUrgency(String appliedUrgency) {
        this.appliedUrgency = appliedUrgency;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

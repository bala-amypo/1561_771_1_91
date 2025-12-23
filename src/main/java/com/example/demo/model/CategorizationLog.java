package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class CategorizationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Ticket ticket;

    @ManyToOne(optional = false)
    private CategorizationRule appliedRule;

    private String matchedKeyword;
    private String assignedCategory;
    private String assignedUrgency;

    private LocalDateTime loggedAt;

    public CategorizationLog() {
    }

    public CategorizationLog(
            Ticket ticket,
            CategorizationRule appliedRule,
            String matchedKeyword,
            String assignedCategory,
            String assignedUrgency) {

        this.ticket = ticket;
        this.appliedRule = appliedRule;
        this.matchedKeyword = matchedKeyword;
        this.assignedCategory = assignedCategory;
        this.assignedUrgency = assignedUrgency;
    }

    @PrePersist
    protected void prePersist() {
        this.loggedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public CategorizationRule getAppliedRule() {
        return appliedRule;
    }

    public void setAppliedRule(CategorizationRule appliedRule) {
        this.appliedRule = appliedRule;
    }

    public String getMatchedKeyword() {
        return matchedKeyword;
    }

    public void setMatchedKeyword(String matchedKeyword) {
        this.matchedKeyword = matchedKeyword;
    }

    public String getAssignedCategory() {
        return assignedCategory;
    }

    public void setAssignedCategory(String assignedCategory) {
        this.assignedCategory = assignedCategory;
    }

    public String getAssignedUrgency() {
        return assignedUrgency;
    }

    public void setAssignedUrgency(String assignedUrgency) {
        this.assignedUrgency = assignedUrgency;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }
}

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
    private String assignedCategory;
    private String assignedUrgency;

    private LocalDateTime loggedAt;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private CategorizationRule appliedRule;

    public CategorizationLog() {}

    public CategorizationLog(Ticket ticket, CategorizationRule appliedRule,
                             String matchedKeyword, String assignedCategory, String assignedUrgency) {
        this.ticket = ticket;
        this.appliedRule = appliedRule;
        this.matchedKeyword = matchedKeyword;
        this.assignedCategory = assignedCategory;
        this.assignedUrgency = assignedUrgency;
    }

    @PrePersist
    public void prePersist() {
        this.loggedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMatchedKeyword() { return matchedKeyword; }
    public void setMatchedKeyword(String matchedKeyword) { this.matchedKeyword = matchedKeyword; }

    public String getAssignedCategory() { return assignedCategory; }
    public void setAssignedCategory(String assignedCategory) { this.assignedCategory = assignedCategory; }

    public String getAssignedUrgency() { return assignedUrgency; }
    public void setAssignedUrgency(String assignedUrgency) { this.assignedUrgency = assignedUrgency; }

    public LocalDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }

    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }

    public CategorizationRule getAppliedRule() { return appliedRule; }
    public void setAppliedRule(CategorizationRule appliedRule) { this.appliedRule = appliedRule; }
}

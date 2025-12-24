package com.example.demo.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.model.*;

@Component
public class TicketCategorizationEngine {

    public Urgency categorize(
            Ticket ticket,
            List<Category> categories,
            List<CategorizationRule> rules,
            List<UrgencyPolicy> policies,
            List<CategorizationLog> logs
    ) {

        Urgency finalUrgency = Urgency.LOW;
        String description = ticket.getDescription().toLowerCase();

        // RULE BASED
        for (CategorizationRule rule : rules) {
            if (description.contains(rule.getKeyword().toLowerCase())) {
                finalUrgency = Urgency.HIGH;

                CategorizationLog log = new CategorizationLog();
                log.setTicket(ticket);
                log.setAppliedRule(rule);
                logs.add(log);
                break;
            }
        }

        // POLICY OVERRIDE
        for (UrgencyPolicy policy : policies) {
            if (description.contains(policy.getKeyword().toLowerCase())) {
                finalUrgency = policy.getUrgencyOverride();
                break;
            }
        }

        return finalUrgency;
    }
}

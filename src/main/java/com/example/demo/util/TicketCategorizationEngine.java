package com.example.demo.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.model.*;

@Component
public class TicketCategorizationEngine {

    public boolean categorize(
            Ticket ticket,
            List<Category> categories,
            List<CategorizationRule> rules,
            List<UrgencyPolicy> policies,
            List<CategorizationLog> logs
    ) {

        boolean matched = false;

        // 🔹 Rule-based category assignment
        for (CategorizationRule rule : rules) {
            if (ticket.getDescription() != null &&
                ticket.getDescription().toLowerCase()
                    .contains(rule.getKeyword().toLowerCase())) {

                ticket.setAssignedCategory(rule.getCategory());
                ticket.setUrgencyLevel(
                        rule.getCategory().getDefaultUrgency()
                );

                matched = true;
                break;
            }
        }

        // 🔹 Policy-based urgency override
        for (UrgencyPolicy policy : policies) {
            if (ticket.getDescription() != null &&
                ticket.getDescription().toLowerCase()
                    .contains(policy.getKeyword().toLowerCase())) {

                ticket.setUrgencyLevel(policy.getUrgencyOverride());
            }
        }

        // 🔹 Default urgency if nothing matched
        if (ticket.getUrgencyLevel() == null) {
            ticket.setUrgencyLevel("LOW");
        }

        return matched;
    }
}

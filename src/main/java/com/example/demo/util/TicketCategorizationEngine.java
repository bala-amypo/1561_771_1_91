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

        for (CategorizationRule rule : rules) {
            if (ticket.getDescription()
                    .toLowerCase()
                    .contains(rule.getKeyword().toLowerCase())) {

                ticket.setAssignedCategory(rule.getCategory());
                ticket.setUrgencyLevel(rule.getCategory().getDefaultUrgency());

                matched = true;
                break;
            }
        }

        // policy override
        for (UrgencyPolicy policy : policies) {
            if (ticket.getDescription()
                    .toLowerCase()
                    .contains(policy.getKeyword().toLowerCase())) {

                ticket.setUrgencyLevel(policy.getUrgencyOverride());
            }
        }

        // ✅ default LOW if nothing matched
        if (!matched && ticket.getUrgencyLevel() == null) {
            ticket.setUrgencyLevel("LOW");
        }

        return matched;
    }
}

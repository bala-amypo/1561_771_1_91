package com.example.demo.util;

import java.util.List;

import com.example.demo.model.*;

public class TicketCategorizationEngine {

    public static void categorize(
            Ticket ticket,
            List<Category> categories,
            List<CategorizationRule> rules,
            List<UrgencyPolicy> policies,
            List<CategorizationLog> logs
    ) {

        Category matchedCategory = null;

        // 1️⃣ Match category using rules
        for (CategorizationRule rule : rules) {
            if (ticket.getDescription().toLowerCase()
                    .contains(rule.getKeyword().toLowerCase())) {

                matchedCategory = rule.getCategory();
                break;
            }
        }

        if (matchedCategory == null && !categories.isEmpty()) {
            matchedCategory = categories.get(0);
        }

        ticket.setAssignedCategory(matchedCategory);

        // 2️⃣ Determine urgency
        String urgency = "LOW";

        for (UrgencyPolicy policy : policies) {
            if (ticket.getDescription().toLowerCase()
                    .contains(policy.getKeyword().toLowerCase())) {
                urgency = policy.getUrgencyOverride();
                break;
            }
        }

        if (urgency == null && matchedCategory != null) {
            urgency = matchedCategory.getDefaultUrgency();
        }

        ticket.setUrgencyLevel(urgency);

        // ❌ ENGINE MUST NOT ADD LOGS
        // ✅ logs handled ONLY by service layer
    }
}

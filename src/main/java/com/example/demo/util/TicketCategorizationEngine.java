package com.example.demo.util;

import java.util.List;

import com.example.demo.model.Category;
import com.example.demo.model.CategorizationLog;
import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Ticket;
import com.example.demo.model.UrgencyLevel;
import com.example.demo.model.UrgencyPolicy;

public class TicketCategorizationEngine {

    /**
     * Core categorization logic.
     * This class MUST be usable independently (no Spring, no repositories).
     */
    public UrgencyLevel categorize(
            Ticket ticket,
            List<Category> categories,
            List<CategorizationRule> rules,
            List<UrgencyPolicy> policies,
            List<CategorizationLog> logs
    ) {

        // ✅ DEFAULT (required by tests)
        UrgencyLevel finalUrgency = UrgencyLevel.LOW;

        String description =
                ticket.getDescription() == null
                        ? ""
                        : ticket.getDescription().toLowerCase();

        /* -------------------------------------------------
           1. APPLY URGENCY POLICIES (HIGHEST PRIORITY)
           ------------------------------------------------- */
        for (UrgencyPolicy policy : policies) {
            if (policy.getKeyword() != null &&
                description.contains(policy.getKeyword().toLowerCase())) {

                finalUrgency = policy.getUrgencyOverride();
                return finalUrgency; // override immediately
            }
        }

        /* -------------------------------------------------
           2. APPLY CATEGORIZATION RULES
           ------------------------------------------------- */
        for (CategorizationRule rule : rules) {
            if (rule.getKeyword() != null &&
                description.contains(rule.getKeyword().toLowerCase())) {

                if (rule.getCategory() != null &&
                    rule.getCategory().getDefaultUrgency() != null) {

                    finalUrgency = rule.getCategory().getDefaultUrgency();
                }
                break;
            }
        }

        /* -------------------------------------------------
           3. DEFAULT FALLBACK (LOW)
           ------------------------------------------------- */
        return finalUrgency;
    }
}

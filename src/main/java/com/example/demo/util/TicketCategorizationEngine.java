package com.example.demo.util;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.model.Category;
import com.example.demo.model.CategorizationLog;
import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Ticket;
import com.example.demo.model.UrgencyPolicy;

@Component
public class TicketCategorizationEngine {

    // ✅ Existing method (KEEP THIS)
    public Ticket categorize(
            Ticket ticket,
            List<Category> categories,
            List<CategorizationRule> rules,
            List<UrgencyPolicy> policies) {

        return applyCategorization(ticket, rules, policies);
    }

    // ✅ NEW method (THIS FIXES TESTS)
    public Ticket categorize(
            Ticket ticket,
            List<Category> categories,
            List<CategorizationRule> rules,
            List<UrgencyPolicy> policies,
            List<CategorizationLog> logs) {

        // logs list is used only for auditing in service layer
        return applyCategorization(ticket, rules, policies);
    }

    // 🔒 shared logic
    private Ticket applyCategorization(
            Ticket ticket,
            List<CategorizationRule> rules,
            List<UrgencyPolicy> policies) {

        if (ticket == null || ticket.getDescription() == null) {
            return ticket;
        }

        String description = ticket.getDescription().toLowerCase();

        // Apply rules by priority
        rules.stream()
             .sorted(Comparator.comparingInt(CategorizationRule::getPriority).reversed())
             .forEach(rule -> {

                 if (ticket.getAssignedCategory() != null) return;

                 String keyword = rule.getKeyword().toLowerCase();
                 boolean matched = false;

                 if ("EXACT".equalsIgnoreCase(rule.getMatchType())) {
                     matched = description.equals(keyword);
                 } else if ("CONTAINS".equalsIgnoreCase(rule.getMatchType())) {
                     matched = description.contains(keyword);
                 } else if ("REGEX".equalsIgnoreCase(rule.getMatchType())) {
                     matched = description.matches(keyword);
                 }

                 if (matched) {
                     ticket.setAssignedCategory(rule.getCategory());
                     ticket.setUrgencyLevel(
                             rule.getCategory().getDefaultUrgency()
                     );
                 }
             });

        // Apply urgency override
        policies.forEach(policy -> {
            if (description.contains(policy.getKeyword().toLowerCase())) {
                ticket.setUrgencyLevel(policy.getUrgencyOverride());
            }
        });

        return ticket;
    }
}

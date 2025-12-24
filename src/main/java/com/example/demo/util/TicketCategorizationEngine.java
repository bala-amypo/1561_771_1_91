package com.example.demo.util;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.model.Category;
import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Ticket;
import com.example.demo.model.UrgencyPolicy;

@Component
public class TicketCategorizationEngine {

    /**
     * IMPORTANT:
     * Method signature MUST MATCH tests exactly.
     */
    public Ticket categorize(
            Ticket ticket,
            List<Category> categories,
            List<CategorizationRule> rules,
            List<UrgencyPolicy> policies) {

        if (ticket == null || ticket.getDescription() == null) {
            return ticket;
        }

        String description = ticket.getDescription().toLowerCase();

        // 1️⃣ Apply Categorization Rules (highest priority first)
        rules.stream()
             .sorted(Comparator.comparingInt(CategorizationRule::getPriority).reversed())
             .forEach(rule -> {

                 if (ticket.getAssignedCategory() != null) {
                     return; // already categorized
                 }

                 String keyword = rule.getKeyword().toLowerCase();
                 String matchType = rule.getMatchType();

                 boolean matched = false;

                 if ("EXACT".equalsIgnoreCase(matchType)) {
                     matched = description.equals(keyword);
                 } else if ("CONTAINS".equalsIgnoreCase(matchType)) {
                     matched = description.contains(keyword);
                 } else if ("REGEX".equalsIgnoreCase(matchType)) {
                     matched = description.matches(keyword);
                 }

                 if (matched) {
                     Category category = rule.getCategory();
                     ticket.setAssignedCategory(category);
                     ticket.setUrgencyLevel(category.getDefaultUrgency());
                 }
             });

        // 2️⃣ Apply Urgency Policies (override urgency)
        policies.forEach(policy -> {
            if (policy.getKeyword() != null &&
                description.contains(policy.getKeyword().toLowerCase())) {

                ticket.setUrgencyLevel(policy.getUrgencyOverride());
            }
        });

        return ticket;
    }
}

package com.example.demo.util;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.model.CategorizationLog;
import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Category;
import com.example.demo.model.Ticket;
import com.example.demo.model.UrgencyPolicy;

@Component   // 🔥 THIS IS THE FIX
public class TicketCategorizationEngine {

    public void categorize(
            Ticket ticket,
            List<Category> categories,
            List<CategorizationRule> rules,
            List<UrgencyPolicy> policies,
            List<CategorizationLog> logs
    ) {

        // Default urgency
        ticket.setUrgencyLevel("LOW");

        // Find highest priority rule
        CategorizationRule matchedRule = rules.stream()
                .sorted(Comparator.comparingInt(CategorizationRule::getPriority).reversed())
                .filter(rule -> matches(ticket, rule))
                .findFirst()
                .orElse(null);

        if (matchedRule != null) {
            Category category = matchedRule.getCategory();

            ticket.setAssignedCategory(category);
            ticket.setUrgencyLevel(category.getDefaultUrgency());

            CategorizationLog log = new CategorizationLog();
            log.setTicket(ticket);
            log.setAppliedRule(matchedRule);
            log.setMatchedKeyword(matchedRule.getKeyword());
            log.setAssignedCategory(category.getCategoryName());
            log.setAssignedUrgency(ticket.getUrgencyLevel());

            logs.add(log);
        }

        // Apply policy override
        for (UrgencyPolicy policy : policies) {
            if (ticket.getDescription() != null &&
                ticket.getDescription().toLowerCase()
                        .contains(policy.getKeyword().toLowerCase())) {

                ticket.setUrgencyLevel(policy.getUrgencyOverride());

                if (!logs.isEmpty()) {
                    logs.get(logs.size() - 1)
                        .setAssignedUrgency(ticket.getUrgencyLevel());
                }
            }
        }
    }

    private boolean matches(Ticket ticket, CategorizationRule rule) {
        String keyword = rule.getKeyword().toLowerCase();

        String title = ticket.getTitle() == null ? "" : ticket.getTitle().toLowerCase();
        String desc  = ticket.getDescription() == null ? "" : ticket.getDescription().toLowerCase();

        switch (rule.getMatchType()) {
            case "EXACT":
                return title.equals(keyword) || desc.equals(keyword);
            case "CONTAINS":
                return title.contains(keyword) || desc.contains(keyword);
            case "REGEX":
                return title.matches(keyword) || desc.matches(keyword);
            default:
                return false;
        }
    }
}

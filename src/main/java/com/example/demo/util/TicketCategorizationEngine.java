package com.example.demo.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.model.CategorizationLog;
import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Category;
import com.example.demo.model.Ticket;
import com.example.demo.model.UrgencyPolicy;

@Component
public class TicketCategorizationEngine {
    public List<CategorizationLog> categorize(Ticket ticket,List<Category>categories,List<CategorizationRule>rules, List<UrgencyPolicy> policies){
        List<CategorizationLog> logs = new ArrayList<>();

        rules.sort(Comparator.comparingInt(CategorizationRule::getPriority).reversed());

        String description = ticket.getDescription().toLowerCase();
        for(CategorizationRule rule: rules){
            if(matches(rule, description)){
                    Category category = rule.getCategory();
                    ticket.setAssignedCategory(category);

                    String urgency= category.getDefaultUrgency();

                    for(UrgencyPolicy policy: policies){
                        if(description.contains(policy.getKeyword().toLowerCase())){
                            urgency = policy.getUrgencyOverride();
                            break;
                        }
                    }
                    ticket.setUrgencyLevel(urgency);
                    CategorizationLog log = new CategorizationLog(
                        ticket,
                        rule,
                        rule.getKeyword(),
                        category.getCategoryName(),
                        urgency
                    );
                    logs.add(log);
                    break;
            }
        }
        return logs;
    }
    private boolean matches(CategorizationRule rule,String text){
        String keyword= rule.getKeyword().toLowerCase();
        return switch (rule.getMatchType()){
            case "EXACT" -> text.equals(keyword);
            case "CONTAINS" -> text.contains(keyword);
            case "REGEX" -> text.matches(keyword);
            default -> false;
        };
    }
}

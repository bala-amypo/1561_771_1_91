package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CategorizationLog;
import com.example.demo.model.CategorizationRule;
import com.example.demo.model.Category;
import com.example.demo.model.Ticket;
import com.example.demo.model.UrgencyPolicy;
import com.example.demo.repository.CategorizationLogRepository;
import com.example.demo.repository.CategorizationRuleRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UrgencyPolicyRepository;
import com.example.demo.service.CategorizationEngineService;
import com.example.demo.util.TicketCategorizationEngine;

@Service
public class CategorizationEngineServiceImpl implements CategorizationEngineService {

    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private final CategorizationRuleRepository ruleRepository;
    private final UrgencyPolicyRepository policyRepository;
    private final CategorizationLogRepository logRepository;

    public CategorizationEngineServiceImpl(
            TicketRepository ticketRepository,
            CategoryRepository categoryRepository,
            CategorizationRuleRepository ruleRepository,
            UrgencyPolicyRepository policyRepository,
            CategorizationLogRepository logRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.categoryRepository = categoryRepository;
        this.ruleRepository = ruleRepository;
        this.policyRepository = policyRepository;
        this.logRepository = logRepository;
    }

    // ✅ 1. Categorize ticket
    @Override
    public Ticket categorizeTicket(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        List<Category> categories = categoryRepository.findAll();
        List<CategorizationRule> rules = ruleRepository.findAll();
        List<UrgencyPolicy> policies = policyRepository.findAll();
        List<CategorizationLog> logs = new ArrayList<>();

        // static utility engine (NOT a Spring bean)
        TicketCategorizationEngine.categorize(
                ticket, categories, rules, policies, logs
        );

        ticketRepository.save(ticket);
        logRepository.saveAll(logs);

        return ticket;
    }

    // ✅ 2. Get single log
    @Override
    public CategorizationLog getLog(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found"));
    }

    // ✅ 3. Get logs for a ticket
    @Override
    public List<CategorizationLog> getLogsForTicket(Long ticketId) {
        return logRepository.findByTicketId(ticketId);
    }
}

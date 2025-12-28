package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.CategorizationLog;
import com.example.demo.model.Ticket;
import com.example.demo.service.CategorizationEngineService;

@RestController
@RequestMapping("/api/categorize")
public class CategorizationEngineController {

    private final CategorizationEngineService engineService;

    public CategorizationEngineController(CategorizationEngineService engineService) {
        this.engineService = engineService;
    }

    
    @PostMapping("/run/{ticketId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ticket> run(@PathVariable Long ticketId) {
        return ResponseEntity.ok(engineService.categorizeTicket(ticketId));
    }

    
    @GetMapping("/logs/{ticketId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CategorizationLog>> getLogs(@PathVariable Long ticketId) {
        return ResponseEntity.ok(engineService.getLogsForTicket(ticketId));
    }

   
    @GetMapping("/log/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategorizationLog> getLog(@PathVariable Long id) {
        return ResponseEntity.ok(engineService.getLog(id));
    }
}

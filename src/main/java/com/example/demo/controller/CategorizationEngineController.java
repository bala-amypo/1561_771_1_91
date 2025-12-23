package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public ResponseEntity<Ticket> runCategorization(@PathVariable Long ticketId) {
    return ResponseEntity.ok(engineService.categorizeTicket(ticketId));
}

@GetMapping("/logs/{ticketId}")
public ResponseEntity<List<CategorizationLog>> getLogs(@PathVariable Long ticketId) {
    return ResponseEntity.ok(engineService.getLogsForTicket(ticketId));
}

@GetMapping("/log/{id}")
public ResponseEntity<CategorizationLog> getLog(@PathVariable Long id) {
    return ResponseEntity.ok(engineService.getLog(id));
}


}
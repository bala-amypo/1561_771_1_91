package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // ✅ USER can raise ticket
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.createTicket(ticket));
    }

    // ✅ ADMIN can view all tickets
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Ticket>> getAll() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    // ✅ ADMIN can view any ticket
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ticket> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicket(id));
    }
}

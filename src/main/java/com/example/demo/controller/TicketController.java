package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ticket;
import com.example.demo.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

private final TicketService ticketService;

public TicketController(TicketService ticketService) {
    this.ticketService = ticketService;
}

@PostMapping
public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
    return ResponseEntity.ok(ticketService.createTicket(ticket));
}

@GetMapping
public ResponseEntity<List<Ticket>> getAllTickets() {
    return ResponseEntity.ok(ticketService.getAllTickets());
}

@GetMapping("/{id}")
public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
    return ResponseEntity.ok(ticketService.getTicket(id));
}


}
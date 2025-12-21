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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;
    TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }
    @PostMapping
    public ResponseEntity<Ticket> createData(@Valid @RequestBody Ticket ticket){
        return ResponseEntity.status(201).body(ticketService.createTicket(ticket));
    }
    @GetMapping
    public ResponseEntity<List<Ticket>> getData(){
        return ResponseEntity.status(200).body(ticketService.getAllTickets());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id){
        return ResponseEntity.status(200).body(ticketService.getTicket(id));
    }
}

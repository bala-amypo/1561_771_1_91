package com.example.demo.controller;

import java.util.List;

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
    public Ticket createData(@Valid @RequestBody Ticket ticket){
        return ticketService.createTicket(ticket);
    }
    @GetMapping
    public List<Ticket> getData(){
        return ticketService.getAllTickets();
    }
    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable Long id){
        return ticketService.getTicket(id);
    }
}

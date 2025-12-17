package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    TicketServiceImpl(TicketRepository ticketRepository){
        this.ticketRepository=ticketRepository;
    }
    public Ticket createTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }
    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }
    public Ticket getTicket(Long id){
        Optional<Ticket>opTicket= ticketRepository.findById(id);
        return opTicket.orElse(null);
    }
    
}

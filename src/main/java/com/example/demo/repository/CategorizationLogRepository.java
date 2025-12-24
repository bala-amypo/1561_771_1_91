package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CategorizationLog;

public interface CategorizationLogRepository
        extends JpaRepository<CategorizationLog, Long> {

    List<CategorizationLog> findByTicketId(Long ticketId);

    List<CategorizationLog> findByTicket_Id(Long ticketId);
}

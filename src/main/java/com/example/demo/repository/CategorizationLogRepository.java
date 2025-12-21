package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CategorizationLog;
@Repository
public interface CategorizationLogRepository extends JpaRepository<CategorizationLog,Long>{
    List<CategorizationLog> findByTicketId(Long ticketId);
     Optional<CategorizationLog> findById(Long id);
     List<CategorizationLog> findAll();
     List<CategorizationLog> findByTicket_Id(Long ticketId);
    
} 
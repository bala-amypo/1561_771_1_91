package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CategorizationLog;
import com.example.demo.model.Ticket;

public interface CategorizationEngineService {

    Ticket categorizeTicket(Long ticketId);

    List<CategorizationLog> getLogsForTicket(Long ticketId);

    CategorizationLog getLog(Long id);
}

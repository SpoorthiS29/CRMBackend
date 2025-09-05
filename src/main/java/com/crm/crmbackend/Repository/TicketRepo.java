package com.crm.crmbackend.Repository;

import com.crm.crmbackend.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
}
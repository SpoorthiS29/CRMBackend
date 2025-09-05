package com.crm.crmbackend.Service;

import com.crm.crmbackend.Entity.Ticket;
import com.crm.crmbackend.Repository.TicketRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepo ticketRepo;

    public TicketService(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepo.findById(id);
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    public Ticket updateTicket(Long id, Ticket ticketDetails) {
        Ticket ticket = ticketRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setCustomer(ticketDetails.getCustomer());
        ticket.setIssue(ticketDetails.getIssue());
        ticket.setAssignedTo(ticketDetails.getAssignedTo());
        ticket.setStatus(ticketDetails.getStatus());
        ticket.setResolutionDate(ticketDetails.getResolutionDate());
        return ticketRepo.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepo.deleteById(id);
    }
}
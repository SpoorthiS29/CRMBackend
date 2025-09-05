package com.crm.crmbackend.Controller;

import com.crm.crmbackend.Repository.CustomerRepo;
import com.crm.crmbackend.Repository.DealRepo;
import com.crm.crmbackend.Repository.TicketRepo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@PreAuthorize("hasAnyRole('ADMIN', 'SALES', 'SUPPORT')")
@RestController
public class ReportController {

    private final CustomerRepo customerRepo;
    private final DealRepo dealRepo;
    private final TicketRepo ticketRepo;

    public ReportController(CustomerRepo customerRepo, DealRepo dealRepo, TicketRepo ticketRepo) {
        this.customerRepo = customerRepo;
        this.dealRepo = dealRepo;
        this.ticketRepo = ticketRepo;
    }

    @GetMapping("/reports")
    public Map<String, Object> getSummaryReport() {
        Map<String, Object> report = new HashMap<>();
        report.put("totalCustomers", customerRepo.count());
        report.put("totalDeals", dealRepo.count());
        report.put("totalTickets", ticketRepo.count());
        report.put("totalSales", dealRepo.findAll().stream().mapToDouble(i -> i.getAmount() != null ? i.getAmount() : 0.0).sum());
        
        return report;
    }
}
package com.crm.crmbackend.Controller;

import com.crm.crmbackend.Entity.Lead;
import com.crm.crmbackend.Service.LeadService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
@RestController
@RequestMapping("/leads")
public class LeadController {
    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @GetMapping
    public List<Lead> getAllLeads() {
        return leadService.getAllLeads();
    }

    @GetMapping("/{id}")
    public Lead getLeadById(@PathVariable Long id) {
        return leadService.getLeadById(id)
            .orElseThrow(() -> new RuntimeException("Lead not found"));
    }

    @PostMapping
    public Lead createLead(@RequestBody Lead lead) {
        return leadService.createLead(lead);
    }

    @PutMapping("/{id}")
    public Lead updateLead(@PathVariable Long id, @RequestBody Lead lead) {
        return leadService.updateLead(id, lead);
    }

    @DeleteMapping("/{id}")
    public void deleteLead(@PathVariable Long id) {
        leadService.deleteLead(id);
    }
}
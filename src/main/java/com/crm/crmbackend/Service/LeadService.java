package com.crm.crmbackend.Service;

import com.crm.crmbackend.Entity.Lead;
import com.crm.crmbackend.Repository.LeadRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadService {
    private final LeadRepo leadRepo;

    public LeadService(LeadRepo leadRepo) {
        this.leadRepo = leadRepo;
    }

    public List<Lead> getAllLeads() {
        return leadRepo.findAll();
    }

    public Optional<Lead> getLeadById(Long id) {
        return leadRepo.findById(id);
    }

    public Lead createLead(Lead lead) {
        return leadRepo.save(lead);
    }

    public Lead updateLead(Long id, Lead leadDetails) {
        Lead lead = leadRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Lead not found"));
        lead.setCustomer(leadDetails.getCustomer());
        lead.setSource(leadDetails.getSource());
        lead.setStatus(leadDetails.getStatus());
        lead.setAssignedTo(leadDetails.getAssignedTo());
        return leadRepo.save(lead);
    }

    public void deleteLead(Long id) {
        leadRepo.deleteById(id);
    }
}
package com.crm.crmbackend.Service;

import com.crm.crmbackend.Entity.Deal;
import com.crm.crmbackend.Repository.DealRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealService {
    private final DealRepo dealRepo;

    public DealService(DealRepo dealRepo) {
        this.dealRepo = dealRepo;
    }

    public List<Deal> getAllDeals() {
        return dealRepo.findAll();
    }

    public Optional<Deal> getDealById(Long id) {
        return dealRepo.findById(id);
    }

    public Deal createDeal(Deal deal) {
        return dealRepo.save(deal);
    }

    public Deal updateDeal(Long id, Deal dealDetails) {
        Deal deal = dealRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Deal not found"));
        deal.setTitle(dealDetails.getTitle());
        deal.setAmount(dealDetails.getAmount());
        deal.setStage(dealDetails.getStage());
        deal.setCloseDate(dealDetails.getCloseDate());
        deal.setCustomer(dealDetails.getCustomer());
        return dealRepo.save(deal);
    }

    public void deleteDeal(Long id) {
        dealRepo.deleteById(id);
    }
}
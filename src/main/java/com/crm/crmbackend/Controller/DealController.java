package com.crm.crmbackend.Controller;

import com.crm.crmbackend.Entity.Deal;
import com.crm.crmbackend.Service.DealService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
@RestController
@RequestMapping("/deals")

public class DealController {
    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping
    public List<Deal> getAllDeals() {
        return dealService.getAllDeals();
    }

    @GetMapping("/{id}")
    public Deal getDealById(@PathVariable Long id) {
        return dealService.getDealById(id)
            .orElseThrow(() -> new RuntimeException("Deal not found"));
    }

    @PostMapping
    public Deal createDeal(@RequestBody Deal deal) {
        return dealService.createDeal(deal);
    }

    @PutMapping("/{id}")
    public Deal updateDeal(@PathVariable Long id, @RequestBody Deal deal) {
        return dealService.updateDeal(id, deal);
    }

    @DeleteMapping("/{id}")
    public void deleteDeal(@PathVariable Long id) {
        dealService.deleteDeal(id);
    }
}
package com.crm.crmbackend.Controller;

import com.crm.crmbackend.Repository.CustomerRepo;
import com.crm.crmbackend.Entity.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class DashboardController {

    private final CustomerRepo customerRepo;

    public DashboardController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @GetMapping("/")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        List<Customer> customers = customerRepo.findAll();

        stats.put("totalCustomers", customers.size());
        stats.put("recentCustomers", customers.stream()
                .sorted((a, b) -> b.getId().compareTo(a.getId()))
                .limit(5)
                .toList());

        // Add more stats as needed

        return stats;
    }
}

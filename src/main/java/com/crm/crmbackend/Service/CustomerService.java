package com.crm.crmbackend.Service;

import com.crm.crmbackend.Entity.Customer;
import com.crm.crmbackend.Repository.CustomerRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepo.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setName(customerDetails.getName());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhone(customerDetails.getPhone());
        return customerRepo.save(customer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (customer.getTickets() != null) {
            customer.getTickets().forEach(ticket -> ticket.setCustomer(null));
        }

        if (customer.getDeals() != null) {
            customer.getDeals().forEach(deal -> deal.setCustomer(null));
        }

        customerRepo.delete(customer);
    }

}
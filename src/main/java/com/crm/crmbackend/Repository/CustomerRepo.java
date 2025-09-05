package com.crm.crmbackend.Repository;

import com.crm.crmbackend.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}

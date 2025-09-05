package com.crm.crmbackend.Repository;

import com.crm.crmbackend.Entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepo extends JpaRepository<Deal, Long> {
}
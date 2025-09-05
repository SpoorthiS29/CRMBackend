package com.crm.crmbackend.Repository;

import com.crm.crmbackend.Entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepo extends JpaRepository<Lead, Long> {
}
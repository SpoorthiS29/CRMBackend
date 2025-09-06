package com.crm.crmbackend.Entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    // One customer can have many tickets
    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("customer") 
    private List<Ticket> tickets;

    // One customer can have many deals
    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("customer") 
    private List<Deal> deals;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }

    public List<Deal> getDeals() { return deals; }
    public void setDeals(List<Deal> deals) { this.deals = deals; }
}

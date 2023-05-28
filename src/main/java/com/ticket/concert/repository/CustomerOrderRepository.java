package com.ticket.concert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticket.concert.domain.CustomerOrder;
import com.ticket.concert.domain.OrderTicket;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long>{
    
}

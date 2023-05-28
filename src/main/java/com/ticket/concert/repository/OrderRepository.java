package com.ticket.concert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ticket.concert.domain.OrderTicket;

public interface OrderRepository extends JpaRepository<OrderTicket, Long>{
    
}

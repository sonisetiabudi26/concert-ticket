package com.ticket.concert.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.concert.domain.Concert;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {
	// Page<Concert> findall(Pageable pageable);
    
}

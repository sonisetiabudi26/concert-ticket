package com.ticket.concert.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ticket.concert.domain.Concert;
import com.ticket.concert.domain.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "select * from ticket a left join ticket_category b on a.ticket_category_id=b.id where date(start_date) <= :date", nativeQuery = true)
    Page<Ticket> findTicketAvailable(String date,Pageable pageable);

    List<Ticket> findByConcert(Concert concert);

}

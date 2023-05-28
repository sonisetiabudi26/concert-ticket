package com.ticket.concert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ticket.concert.domain.Concert;
import com.ticket.concert.domain.TicketCategory;

@Repository
public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Long> {
    List<TicketCategory> findByConcert(Concert concert);
    @Query(value = "select * from ticket_category where remaining_ticket!=0 and (concert_id=:concertID or date(start_date) BETWEEN :fromdate AND :todate)", nativeQuery = true)
    Page<TicketCategory> findTicketAvailable(String fromdate,String todate,Long concertID,Pageable pageable);

}

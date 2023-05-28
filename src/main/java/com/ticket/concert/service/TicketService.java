package com.ticket.concert.service;

import com.ticket.concert.domain.Concert;
import com.ticket.concert.domain.Ticket;
import com.ticket.concert.domain.TicketCategory;
import com.ticket.concert.exception.MessageException;
import com.ticket.concert.exception.ResourceNotFoundException;
import com.ticket.concert.repository.ConcertRepository;
import com.ticket.concert.repository.TicketCategoryRepository;
import com.ticket.concert.repository.TicketRepository;
import com.ticket.concert.utils.AppConstants;
import com.ticket.concert.utils.PagedResponse;
import com.ticket.concert.vo.ConcertVo;
import com.ticket.concert.vo.TicketCategoryVo;
import com.ticket.concert.vo.TicketVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketCategoryRepository ticketCategoryRepository;

    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private ConcertService concertService;

    @Transactional(readOnly = true)

    public PagedResponse<TicketCategoryVo> getAllTicket(int page, int size) throws Exception {
        try {
            validatePageNumberAndSize(page, size);
            Pageable pageable = PageRequest.of(page, size);

            Page<TicketCategory> tickets = ticketCategoryRepository.findAll(pageable);

            List<TicketCategory> ticket = tickets.getNumberOfElements() == 0 ? Collections.emptyList() : tickets.getContent();
            List<TicketCategoryVo> dataVo = mappingDataTicketAvail(ticket);
            return new PagedResponse<>(dataVo, tickets.getNumber(), tickets.getSize(), tickets.getTotalElements(),
                    tickets.getTotalPages(), tickets.isLast());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("Error");
        }

    }

    public PagedResponse<TicketCategoryVo> getTicketAvailable(String fromdate,String todate,Long concerID,int page, int size) throws Exception {
        try {
            validatePageNumberAndSize(page, size);
            Pageable pageable = PageRequest.of(page, size);

            Page<TicketCategory> tickets = ticketCategoryRepository.findTicketAvailable(fromdate,todate,concerID,pageable);

            List<TicketCategory> ticket = tickets.getNumberOfElements() == 0 ? Collections.emptyList() : tickets.getContent();
            List<TicketCategoryVo> dataVo = mappingDataTicketAvail(ticket);
            return new PagedResponse<>(dataVo, tickets.getNumber(), tickets.getSize(), tickets.getTotalElements(),
                    tickets.getTotalPages(), tickets.isLast());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("Error");
        }

    }
    

    public List<TicketCategoryVo> getTicketCategorybyIDConcert(Concert concert){
       
		List<TicketCategory> tickets = ticketCategoryRepository.findByConcert(concert);

		return mappingDataTicket(tickets);

    
	}

    private void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new MessageException("Page number cannot be less than zero.");
        }

        if (size < 0) {
            throw new MessageException("Size number cannot be less than zero.");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new MessageException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }


    private List<TicketCategoryVo> mappingDataTicket(List<TicketCategory> tickets) {
        List<TicketCategoryVo> listData = new ArrayList<>();
        for (TicketCategory ticket : tickets) {
            TicketCategoryVo data = new TicketCategoryVo();
            data.setDescription(ticket.getDesc());
            data.setStartDate(ticket.getStartDate());
            data.setEndDate(ticket.getEndDate());
            data.setPrice(ticket.getPrice());
            data.setArea(ticket.getArea());
            data.setTicketSlot(ticket.getTicketSlot());
            data.setRemainingTicket(ticket.getRemainingTicket());
            listData.add(data);
        }
            // data.setTicketSlot(ticket.getTicketSlot());
            // data.setRemainingTicket(ticket.getRemainingTicket());
            
       
        return listData;
    }

    private List<TicketCategoryVo> mappingDataTicketAvail(List<TicketCategory> tickets) {
        List<TicketCategoryVo> listData = new ArrayList<>();
        for (TicketCategory ticket : tickets) {
            TicketCategoryVo data = new TicketCategoryVo();
            data.setDescription(ticket.getDesc());
            data.setStartDate(ticket.getStartDate());
            data.setEndDate(ticket.getEndDate());
            data.setPrice(ticket.getPrice());
            data.setArea(ticket.getArea());
            data.setTicketSlot(ticket.getTicketSlot());
            data.setRemainingTicket(ticket.getRemainingTicket());
            ConcertVo dataConcert = concertService.getConcert(ticket.getConcert().getId());
            data.setConcert(dataConcert);
            listData.add(data);
        }
          
       
        return listData;
    }

}

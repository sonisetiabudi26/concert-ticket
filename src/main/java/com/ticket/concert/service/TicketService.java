package com.ticket.concert.service;

import com.ticket.concert.domain.Concert;
import com.ticket.concert.domain.Ticket;
import com.ticket.concert.exception.ResourceNotFoundException;
import com.ticket.concert.repository.TicketRepository;
import com.ticket.concert.utils.AppConstants;
import com.ticket.concert.utils.PagedResponse;
import com.ticket.concert.vo.ConcertVo;
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

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ConcertService concertService;

    @Transactional(readOnly = true)

    public PagedResponse<TicketVo> getAllTicket(int page, int size) throws Exception {
        try {
            validatePageNumberAndSize(page, size);
            Pageable pageable = PageRequest.of(page, size);

            Page<Ticket> tickets = ticketRepository.findAll(pageable);

            List<Ticket> ticket = tickets.getNumberOfElements() == 0 ? Collections.emptyList() : tickets.getContent();
            List<TicketVo> dataVo = mappingData(ticket);
            return new PagedResponse<>(dataVo, tickets.getNumber(), tickets.getSize(), tickets.getTotalElements(),
                    tickets.getTotalPages(), tickets.isLast());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("Error");
        }

    }

    public PagedResponse<TicketVo> getTicketAvailable(int page, int size) throws Exception {
        try {
            validatePageNumberAndSize(page, size);
            Pageable pageable = PageRequest.of(page, size);

            Page<Ticket> tickets = ticketRepository.findTicketAvailable("2023-05-27",pageable);

            List<Ticket> ticket = tickets.getNumberOfElements() == 0 ? Collections.emptyList() : tickets.getContent();
            List<TicketVo> dataVo = mappingData(ticket);
            return new PagedResponse<>(dataVo, tickets.getNumber(), tickets.getSize(), tickets.getTotalElements(),
                    tickets.getTotalPages(), tickets.isLast());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("Error");
        }

    }

    public List<TicketVo> getTicketbyIDConcert(Concert concert) throws Exception{
        try {
		
		List<Ticket> tickets = ticketRepository.findByConcert(concert);

		return mappingDataTicket(tickets);

    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        throw new Exception("Error");
    }
	}

    private void validatePageNumberAndSize(int page, int size) throws Exception {
        if (page < 0) {
            throw new Exception("Page number cannot be less than zero.");
        }

        if (size < 0) {
            throw new Exception("Size number cannot be less than zero.");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new Exception("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

    private List<TicketVo> mappingData(List<Ticket> tickets) throws Exception {
        List<TicketVo> listData = new ArrayList<>();
        TicketVo data = new TicketVo();
        for (Ticket ticket : tickets) {
            data.setSerialNumber(ticket.getSerialNumber());
            data.setSeat(ticket.getSeat());
            data.setPurchaseDate(ticket.getPurchaseDate());
            data.setDescription(ticket.getTicketCategory().getDesc());
            data.setStartDate(ticket.getTicketCategory().getStartDate());
            data.setEndDate(ticket.getTicketCategory().getEndDate());
            data.setPrice(ticket.getTicketCategory().getPrice());
            data.setArea(ticket.getTicketCategory().getArea());
            data.setTicketSlot(ticket.getTicketCategory().getTicketSlot());
            data.setRemainingTicket(ticket.getTicketCategory().getRemainingTicket());
            ConcertVo dataConcert = concertService.getConcert(ticket.getConcert().getId());
            data.setConcert(dataConcert);
            listData.add(data);
        }

        return listData;
    }

    private List<TicketVo> mappingDataTicket(List<Ticket> tickets) throws Exception {
        List<TicketVo> listData = new ArrayList<>();
        TicketVo data = new TicketVo();
        for (Ticket ticket : tickets) {
            data.setSerialNumber(ticket.getSerialNumber());
            data.setSeat(ticket.getSeat());
            data.setPurchaseDate(ticket.getPurchaseDate());
            data.setDescription(ticket.getTicketCategory().getDesc());
            data.setStartDate(ticket.getTicketCategory().getStartDate());
            data.setEndDate(ticket.getTicketCategory().getEndDate());
            data.setPrice(ticket.getTicketCategory().getPrice());
            data.setArea(ticket.getTicketCategory().getArea());
            data.setTicketSlot(ticket.getTicketCategory().getTicketSlot());
            data.setRemainingTicket(ticket.getTicketCategory().getRemainingTicket());
            listData.add(data);
        }

        return listData;
    }

}

package com.ticket.concert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.concert.service.ConcertService;
import com.ticket.concert.service.TicketService;
import com.ticket.concert.utils.AppConstants;
import com.ticket.concert.utils.PagedResponse;
import com.ticket.concert.vo.ConcertVo;
import com.ticket.concert.vo.TicketVo;
@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
	public PagedResponse<TicketVo> getAllTicket(
			@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
			@RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) throws Exception {
		return ticketService.getAllTicket(page, size);
	}

    @GetMapping("/avail")
      public PagedResponse<TicketVo> getTicketAvailable(
        @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
			@RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) throws Exception {
        return ticketService.getTicketAvailable(page,size);
	}
}

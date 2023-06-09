package com.ticket.concert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.concert.service.ConcertService;
import com.ticket.concert.utils.AppConstants;
import com.ticket.concert.utils.PagedResponse;
import com.ticket.concert.vo.ConcertDetailVo;
import com.ticket.concert.vo.ConcertVo;
@RestController
@RequestMapping("/api/concert")
public class ConcertController {

    @Autowired
    private ConcertService concertService;

    @GetMapping
	public PagedResponse<ConcertVo> getAllConcert(
			@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
			@RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) throws Exception {
		return concertService.getAllConcert(page, size);
	}

    @GetMapping("/{id}")
      public ConcertDetailVo getConcert(
        @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
			@RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size ,
            @PathVariable(value = "id") Long id) throws Exception {
        return concertService.getConcertDetail(id);
	}
}

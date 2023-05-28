package com.ticket.concert.vo;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticket.concert.vo.ArtistVo;
import lombok.Data;

@Data
public class TicketCategoryVo {
    // private Long id;
	private String description;
	private double price;
    private Date startDate;
	private Date endDate;
    private String area;
    private Integer ticketSlot;
    private Integer remainingTicket;
    @JsonInclude(JsonInclude.Include.NON_NULL)
	private ConcertVo concert;
	
   
}

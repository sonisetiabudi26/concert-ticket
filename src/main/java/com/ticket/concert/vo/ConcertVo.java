package com.ticket.concert.vo;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class ConcertVo {
    private Long id;
	private String concertName;
	private Date date;
    private String concertGroup;
	private ArtistVo artists;
	private VenueVo venues;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<TicketCategoryVo> tickets;
    
}

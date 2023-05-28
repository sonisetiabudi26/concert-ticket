package com.ticket.concert.vo;


import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ConcertDetailVo {
    private Long id;
	private String concertName;
	private Date date;
    private String concertGroup;
	private ArtistVo artists;
	private VenueVo venues;
    private List<TicketCategoryVo> tickets;
}

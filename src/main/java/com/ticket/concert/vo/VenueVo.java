package com.ticket.concert.vo;

import lombok.Data;

@Data
public class VenueVo {
    private Long id;
	private String venueName;
    private String location;
    private String type;
    private Integer capacity;
	
}
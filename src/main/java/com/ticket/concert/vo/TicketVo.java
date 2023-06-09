package com.ticket.concert.vo;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticket.concert.vo.ArtistVo;
import lombok.Data;

@Data
public class TicketVo {
	private String serialNumber;
	private String seat;
    private Date purchaseDate;
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private String description;
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
	private double price;
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private Date startDate;
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
	private Date endDate;
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private String area;
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private Integer ticketSlot;
    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    private Integer remainingTicket;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private ConcertVo concert;
	
   
}

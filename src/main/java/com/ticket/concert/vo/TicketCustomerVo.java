package com.ticket.concert.vo;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TicketCustomerVo {
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
   
    @JsonInclude(JsonInclude.Include.NON_NULL)
	private ConcertVo concert;
	
   
}

package com.ticket.concert.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticket.concert.vo.ArtistVo;
import lombok.Data;

@Data
public class CustomerOrderVo {
    // private Long id;
	private String deliveryEmailAddress;   
    private Double totalPrice;
    private Double discount;
    private Double finalPrice;
    private Long custId;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Long concertId;

}
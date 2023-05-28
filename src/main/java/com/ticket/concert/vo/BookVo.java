package com.ticket.concert.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BookVo {
    private String deliveryEmailAddress;   
    private Double totalPrice;
    private Double discount;
    private Double finalPrice;
    private Long custId;
    private String seat;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Long concertId;

}

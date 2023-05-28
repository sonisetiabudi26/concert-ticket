package com.ticket.concert.vo;


import java.util.Date;
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
	
   
}

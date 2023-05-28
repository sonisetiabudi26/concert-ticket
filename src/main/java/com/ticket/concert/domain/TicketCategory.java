package com.ticket.concert.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ticket_category")
public class TicketCategory {
    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    @NotBlank
	@Column(name = "description")
	@Size(max = 200)
	private String desc;

    
	@Column(name = "price")
	private Double price;

    @Column(name = "start_date")
	private Date startDate;

    @Column(name = "end_date")
	private Date endDate;

    @Column(name = "area")
	private String area;

	
	@Column(name = "ticket_slot")
	private Integer ticketSlot;

	@Column(name = "remaining_ticket")
	private Integer remainingTicket;

    @ManyToOne
	private Concert concert;

	
   
}
  
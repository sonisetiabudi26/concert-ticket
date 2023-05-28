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
	@Size(max = 50)
	private Double price;

    @Column(name = "start_date")
	@Size(max = 50)
	private Date startDate;

    @Column(name = "end_date")
	@Size(max = 50)
	private Date endDate;

    @Column(name = "area")
	@Size(max = 50)
	private String area;

	@NotBlank
	@Column(name = "ticket_slot")
	@Size(max = 50)
	private Integer ticketSlot;

	@NotBlank
	@Column(name = "remaining_ticket")
	@Size(max = 50)
	private Integer remainingTicket;

    @ManyToOne(fetch = FetchType.LAZY)
	private Concert concert;

	
   
}
  
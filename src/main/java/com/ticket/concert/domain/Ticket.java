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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ticket",uniqueConstraints = { @UniqueConstraint(columnNames = { "serial_number" })})
public class Ticket {
    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    @NotBlank
	@Column(name = "serial_number")
	@Size(max = 50)
	private String serialNumber;

    
	@Column(name = "seat")
	@Size(max = 50)
	private String seat;

    @Column(name = "purchase_date")
	private Date purchaseDate ;

    @ManyToOne(fetch = FetchType.LAZY)
	private Concert concert;

	@ManyToOne
	private TicketCategory ticketCategory;
   
}
  
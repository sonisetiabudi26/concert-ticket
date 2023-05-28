package com.ticket.concert.domain;


import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Data
@Table(name = "order_ticket")
public class OrderTicket implements Serializable {
    private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

   
    
    @ManyToOne(fetch = FetchType.LAZY)
	private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
	private CustomerOrder customerOrder;


}

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
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Data
@Table(name = "customer_order")
public class CustomerOrder implements Serializable {
    private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "order_time")
	private Date orderTime;


    @Column(name = "delivery_email_address")
    @Size(max = 200)
    @Email
	private String deliveryEmailAddress;

    @Column(name = "time_paid")
	private Date timePaid;

    @Column(name = "time_sent")
	private Date timeSent;

    @Column(name = "total_price")
	private Double totalPrice;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "final_price")
    private Double finalPrice;

    
    @OneToMany(mappedBy = "customerOrder")
	private List<OrderTicket> orderTickets;
    
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;

}

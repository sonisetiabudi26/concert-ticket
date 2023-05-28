package com.ticket.concert.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import com.ticket.concert.domain.Artist;
import com.ticket.concert.vo.ArtistVo;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;

@Entity
@NoArgsConstructor
@Data
@Table(name = "concert")
public class Concert implements Serializable {
    private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    @NotBlank
	@Column(name = "concert_name")
	@Size(max = 100)
	private String concertName;

    @Column(name = "date")
	private Date date;

    
    @ManyToOne(fetch = FetchType.LAZY)
	private Artist artist;

    @ManyToOne(fetch = FetchType.LAZY)
	private ConcertGroup concertGroup;

    @ManyToOne(fetch = FetchType.LAZY)
	private Venue venue;

	@OneToMany(mappedBy = "concert", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Ticket> tickets;

	@OneToMany(mappedBy = "concert", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TicketCategory> ticketCats;



}

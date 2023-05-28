package com.ticket.concert.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// @EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "venue")
public class Venue {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    @NotBlank
	@Column(name = "venue_name")
	@Size(max = 50)
	private String name;

    @NotBlank
	@Column(name = "location")
	@Size(max = 50)
	private String location;

    @NotBlank
	@Column(name = "type")
	@Size(max = 50)
	private String type;

    @NotBlank
	@Column(name = "capacity")
	@Size(max = 50)
	private Integer capacity;

	@OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Concert> concerts;

   
} 
  
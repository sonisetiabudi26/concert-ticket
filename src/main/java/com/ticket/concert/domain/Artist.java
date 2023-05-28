package com.ticket.concert.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
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
@Data
@NoArgsConstructor
@Table(name = "artist")
public class Artist implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    @NotBlank
	@Column(name = "artist_name")
	@Size(max = 50)
	private String artistName;

	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Concert> concerts;

    @ManyToOne(fetch = FetchType.LAZY)
	private Genre genre;  
} 
  
package com.ticket.concert.service;

import com.ticket.concert.domain.Concert;
import com.ticket.concert.exception.MessageException;
import com.ticket.concert.exception.ResourceNotFoundException;
import com.ticket.concert.repository.ConcertRepository;
import com.ticket.concert.utils.AppConstants;
import com.ticket.concert.utils.PagedResponse;
import com.ticket.concert.vo.ConcertVo;
import com.ticket.concert.vo.TicketCategoryVo;
import com.ticket.concert.vo.TicketVo;
import com.ticket.concert.vo.VenueVo;
import com.ticket.concert.vo.ArtistVo;
import com.ticket.concert.vo.ConcertDetailVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ConcertService {
    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private TicketService ticketService;

    @Transactional(readOnly = true)

    public PagedResponse<ConcertVo> getAllConcert(int page, int size) {
       
            validatePageNumberAndSize(page, size);
            Pageable pageable = PageRequest.of(page, size);

            Page<Concert> concerts = concertRepository.findAll(pageable);

            List<Concert> concert = concerts.getNumberOfElements() == 0 ? Collections.emptyList()
                    : concerts.getContent();
            List<ConcertVo> dataVo = mappingData(concert);
            return new PagedResponse<>(dataVo, concerts.getNumber(), concerts.getSize(), concerts.getTotalElements(),
                    concerts.getTotalPages(), concerts.isLast());

    }

    public ConcertVo getConcert(Long id) {
       
            Concert concert = concertRepository.findById(id)
                    .orElseThrow(() -> new MessageException("Concert not found"));

            return mappingSingleData(concert);

    }

    public ConcertDetailVo getConcertDetail(Long id) {
      
            Concert concert = concertRepository.findById(id)
                    .orElseThrow(() -> new MessageException("Concert not found"));

            return mappingSingleDataDetail(concert);

       
    }

    private void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new MessageException("Page number cannot be less than zero.");
        }

        if (size < 0) {
            throw new MessageException("Size number cannot be less than zero.");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new MessageException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

    private ConcertVo mappingSingleData(Concert concert) {
        ConcertVo data = new ConcertVo();
        data.setId(concert.getId());
        data.setConcertName(concert.getConcertName());
        data.setDate(concert.getDate());
        data.setConcertGroup(concert.getConcertGroup().getName());
        ArtistVo artists = new ArtistVo();
        artists.setId(concert.getArtist().getId());
        artists.setArtistName(concert.getArtist().getArtistName());
        artists.setGenre(concert.getArtist().getGenre().getGenreName());
        data.setArtists(artists);
        VenueVo venues = new VenueVo();
        venues.setId(concert.getVenue().getId());
        venues.setVenueName(concert.getVenue().getName());
        venues.setLocation(concert.getVenue().getLocation());
        venues.setCapacity(concert.getVenue().getCapacity());
        venues.setType(concert.getVenue().getType());
        data.setVenues(venues);
        return data;
    }

    private ConcertDetailVo mappingSingleDataDetail(Concert concert) {
        ConcertDetailVo data = new ConcertDetailVo();
        data.setId(concert.getId());
        data.setConcertName(concert.getConcertName());
        data.setDate(concert.getDate());
        data.setConcertGroup(concert.getConcertGroup().getName());
        ArtistVo artists = new ArtistVo();
        artists.setId(concert.getArtist().getId());
        artists.setArtistName(concert.getArtist().getArtistName());
        artists.setGenre(concert.getArtist().getGenre().getGenreName());
        data.setArtists(artists);
        VenueVo venues = new VenueVo();
        venues.setId(concert.getVenue().getId());
        venues.setVenueName(concert.getVenue().getName());
        venues.setLocation(concert.getVenue().getLocation());
        venues.setCapacity(concert.getVenue().getCapacity());
        venues.setType(concert.getVenue().getType());
        data.setVenues(venues);
        List<TicketCategoryVo> ticket = ticketService.getTicketCategorybyIDConcert(concert);
        data.setTickets(ticket);
        return data;
    }

    public List<ConcertVo> mappingData(List<Concert> concerts) {
        List<ConcertVo> listData = new ArrayList<>();
       
        for (Concert concert : concerts) {
            ConcertVo data = new ConcertVo();
            data.setId(concert.getId());
            data.setConcertName(concert.getConcertName());
            data.setDate(concert.getDate());
            data.setConcertGroup(concert.getConcertGroup().getName());
            ArtistVo artists = new ArtistVo();
            artists.setId(concert.getArtist().getId());
            artists.setArtistName(concert.getArtist().getArtistName());
            artists.setGenre(concert.getArtist().getGenre().getGenreName());
            data.setArtists(artists);
            VenueVo venues = new VenueVo();
            venues.setId(concert.getVenue().getId());
            venues.setVenueName(concert.getVenue().getName());
            venues.setLocation(concert.getVenue().getLocation());
            venues.setCapacity(concert.getVenue().getCapacity());
            venues.setType(concert.getVenue().getType());
            data.setVenues(venues);
            List<TicketCategoryVo> ticket = ticketService.getTicketCategorybyIDConcert(concert);
             data.setTickets(ticket);
            listData.add(data);
        }

        return listData;
    }

   

}

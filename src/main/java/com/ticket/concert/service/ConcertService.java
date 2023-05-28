package com.ticket.concert.service;

import com.ticket.concert.domain.Concert;
import com.ticket.concert.exception.ResourceNotFoundException;
import com.ticket.concert.repository.ConcertRepository;
import com.ticket.concert.utils.AppConstants;
import com.ticket.concert.utils.PagedResponse;
import com.ticket.concert.vo.ConcertVo;
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

@Service
public class ConcertService {
    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private TicketService ticketService;

    @Transactional(readOnly = true)

    public PagedResponse<ConcertVo> getAllConcert(int page, int size) throws Exception {
        try {
            validatePageNumberAndSize(page, size);
            Pageable pageable = PageRequest.of(page, size);

            Page<Concert> concerts = concertRepository.findAll(pageable);

            List<Concert> concert = concerts.getNumberOfElements() == 0 ? Collections.emptyList()
                    : concerts.getContent();
            List<ConcertVo> dataVo = mappingData(concert);
            return new PagedResponse<>(dataVo, concerts.getNumber(), concerts.getSize(), concerts.getTotalElements(),
                    concerts.getTotalPages(), concerts.isLast());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("Error");
        }

    }

    public ConcertVo getConcert(Long id) throws Exception {
        try {

            Concert concert = concertRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Concert", "id", id));

            return mappingSingleData(concert);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("Error");
        }
    }

    public ConcertDetailVo getConcertDetail(Long id) throws Exception {
        try {

            Concert concert = concertRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Concert", "id", id));

            return mappingSingleDataDetail(concert);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error");
        }
    }

    private void validatePageNumberAndSize(int page, int size) throws Exception {
        if (page < 0) {
            throw new Exception("Page number cannot be less than zero.");
        }

        if (size < 0) {
            throw new Exception("Size number cannot be less than zero.");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new Exception("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
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

    private ConcertDetailVo mappingSingleDataDetail(Concert concert) throws Exception {
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
        List<TicketVo> ticket = ticketService.getTicketbyIDConcert(concert);
        data.setTickets(ticket);
        return data;
    }

    private List<ConcertVo> mappingData(List<Concert> concerts) {
        List<ConcertVo> listData = new ArrayList<>();
        ConcertVo data = new ConcertVo();
        for (Concert concert : concerts) {
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
            listData.add(data);
        }

        return listData;
    }

}

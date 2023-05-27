package com.ticket.concert.service;

import com.ticket.concert.domain.Concert;
import com.ticket.concert.exception.ResourceNotFoundException;
import com.ticket.concert.exception.UnauthorizedException;
import com.ticket.concert.repository.ConcertRepository;
import com.ticket.concert.utils.ApiResponse;
import com.ticket.concert.utils.AppConstants;
import com.ticket.concert.utils.PagedResponse;
import com.ticket.concert.vo.ConcertVo;
import com.ticket.concert.vo.GenreVo;
import com.ticket.concert.vo.ArtistVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.ticket.concert.utils.AppConstants.CREATED_AT;
import static com.ticket.concert.utils.AppConstants.ID;
import static com.ticket.concert.utils.AppConstants.TODO;
import static com.ticket.concert.utils.AppConstants.YOU_DON_T_HAVE_PERMISSION_TO_MAKE_THIS_OPERATION;
@Service
public class ConcertService {
	@Autowired
	private ConcertRepository concertRepository;

	@Transactional(readOnly = true)
	
    public PagedResponse<ConcertVo> getAllConcert(int page, int size) throws Exception {
		try {
            validatePageNumberAndSize(page, size);
            Pageable pageable = PageRequest.of(page, size);

            Page<Concert> concerts = concertRepository.findAll(pageable);

            List<Concert> concert = concerts.getNumberOfElements() == 0 ? Collections.emptyList() : concerts.getContent();
            List<ConcertVo> dataVo=mappingData(concert);
            return new PagedResponse<>(dataVo, concerts.getNumber(), concerts.getSize(), concerts.getTotalElements(),
                concerts.getTotalPages(), concerts.isLast());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new Exception("Error");
        }
		
	}

    public ConcertVo getConcert(Long id) throws Exception{
        try {
		
		Concert concert = concertRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id));

		return mappingSingleData(concert);

    } catch (Exception e) {
        // TODO Auto-generated catch block
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

    private ConcertVo mappingSingleData(Concert concert){
		ConcertVo data=new ConcertVo();
		data.setId(concert.getId());
        data.setConcertName(concert.getConcertName());
        data.setDate(concert.getDate());
        data.setConcertGroup(concert.getConcertGroup().getName());
        ArtistVo artists=new ArtistVo();
        artists.setId(concert.getArtist().getId());
        artists.setArtistName(concert.getArtist().getArtistName());
        artists.setGenre(concert.getArtist().getGenre().getGenreName());
        data.setArtists(artists);
		return data;
	}

	private List<ConcertVo> mappingData(List<Concert> concerts){
        List<ConcertVo> listData=new ArrayList<>();
        ConcertVo data=new ConcertVo();
        for (Concert concert : concerts) {
            data.setId(concert.getId());
            data.setConcertName(concert.getConcertName());
            data.setDate(concert.getDate());
            data.setConcertGroup(concert.getConcertGroup().getName());
            ArtistVo artists=new ArtistVo();
            artists.setId(concert.getArtist().getId());
            artists.setArtistName(concert.getArtist().getArtistName());
            artists.setGenre(concert.getArtist().getGenre().getGenreName());
            data.setArtists(artists);
          
            listData.add(data);
        }
        
		
		return listData;
	}

}



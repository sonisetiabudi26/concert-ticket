package com.ticket.concert.vo;

import java.util.Date;
import com.ticket.concert.vo.ArtistVo;
import lombok.Data;

@Data
public class ArtistVo {
    private Long id;
	private String artistName;
    private String genre;
	
}

package com.tanveer.bookmyshow.Service;


import com.tanveer.bookmyshow.Dto.ShowSeatResponseDto;
import com.tanveer.bookmyshow.Entity.ShowSeat;
import com.tanveer.bookmyshow.Repository.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowSeatService {
    private final ShowSeatRepository showSeatRepository;

    public ShowSeatService(ShowSeatRepository showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    public List<ShowSeatResponseDto> fetchShowSeatsByShowId(Long showId){
        return showSeatRepository.findShowsByShowId(showId);
    }
}

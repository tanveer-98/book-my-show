package com.tanveer.bookmyshow.Controllers;


import com.tanveer.bookmyshow.Dto.ShowSeatResponseDto;
import com.tanveer.bookmyshow.Service.ShowSeatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/showseat")
public class ShowSeatController {
    private final ShowSeatService showSeatService;

    public ShowSeatController(ShowSeatService showSeatService) {
        this.showSeatService = showSeatService;
    }

    @GetMapping("/shows/{showId}")
    public List<ShowSeatResponseDto> fetchShowSeatsByShowId(@PathVariable("showId") Long showId){
        return showSeatService.fetchShowSeatsByShowId(showId);
    }
}

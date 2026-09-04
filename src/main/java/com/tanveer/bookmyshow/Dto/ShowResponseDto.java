package com.tanveer.bookmyshow.Dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ShowResponseDto (
        Long  showId,
        Long movieId ,
        String movieName ,
        String thumbnailUrl,
        Integer durationInMinutes ,
        Long theaterId ,
        String theaterAddress,
        Long screenId ,
        LocalDate showDate,
        LocalTime showTime
){

}

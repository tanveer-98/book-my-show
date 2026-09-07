package com.tanveer.bookmyshow.Dto;

import com.tanveer.bookmyshow.Entity.Genre;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public record MovieResponseDto(
        Long id ,
        Integer durationInMinutes ,
        String name ,
        LocalDateTime releaseDate,
        String thumbnailUrl
) {
}

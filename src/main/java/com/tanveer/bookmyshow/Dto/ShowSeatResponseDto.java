package com.tanveer.bookmyshow.Dto;

import com.tanveer.bookmyshow.Entity.Booking;
import com.tanveer.bookmyshow.Entity.Seat;
import com.tanveer.bookmyshow.Entity.ShowSeat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ShowSeatResponseDto (
        Long id , // correct
        LocalDateTime lockedAt,// correct
        ShowSeat.ShowSeatStatus status ,
        Long bookingId,
        String rowNumber,
        String seatNumber,
        Seat.SeatCategory seatType,
        Long showId ,
        BigDecimal price
){
}

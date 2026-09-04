package com.tanveer.bookmyshow.Dto;

import com.tanveer.bookmyshow.Entity.ShowSeat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class BookingResponse {

    private Long bookingId;

    private String movieName;

    private String screenName;

    private LocalTime showTime;

    private List<String> seats;

    private String bookingStatus;

    private LocalDateTime bookingTime;
}

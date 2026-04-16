package com.tanveer.bookmyshow.Controllers;


import com.tanveer.bookmyshow.Dto.BookingResponse;
import com.tanveer.bookmyshow.Entity.Booking;
import com.tanveer.bookmyshow.Entity.ShowSeat;
import com.tanveer.bookmyshow.Service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // User once searching via city or movie they come to this controller

    @PostMapping("/book")
    public BookingResponse booking(@RequestBody List<Long> seatIds){

        return bookingService.bookSeats(seatIds);

    }

}

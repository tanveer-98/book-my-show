package com.tanveer.bookmyshow.Service;


import com.tanveer.bookmyshow.Dto.BookingResponse;
import com.tanveer.bookmyshow.Entity.Booking;
import com.tanveer.bookmyshow.Entity.ShowSeat;

import com.tanveer.bookmyshow.Exception.SeatBookedException;
import com.tanveer.bookmyshow.Exception.SeatNotSelectedException;
import com.tanveer.bookmyshow.Repository.BookingRepository;
import com.tanveer.bookmyshow.Repository.ShowSeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    // booking service willl use booking repository and showSeat Repository // needs both

    private final BookingRepository bookingRepository;
    private final ShowSeatRepository showSeatRepository;

    public BookingService(ShowSeatRepository showSeatRepository, BookingRepository bookingRepository ) {
        this.bookingRepository = bookingRepository;
        this.showSeatRepository = showSeatRepository;
    }

    /*

        1. User selects show
        2. User selects seats
        3. Fetch ShowSeat
        4. Check if already booked
        5. Mark as booked
        6. Save booking

     */


    @Transactional
    public BookingResponse bookSeats(List<Long> showSeatIds) {
    //Takes seat id's for booking the seats
       // fetch the Seat details first
        BookingResponse bookingResponse = new BookingResponse();

        Booking booking  = new Booking();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        if(showSeats.isEmpty()){
            throw new SeatNotSelectedException("No Seat Selected");
        }

        for(ShowSeat showSeat : showSeats ) {
            if (showSeat.getBooking() != null) {
                //   booking.setStatus(booking.setStatus(Status.FAILED)); BAD PRACTICE : put status in payment stage
                throw new SeatBookedException("Seat Selected is already booked"); // runtime exceptions are Handled by Spring , not the caller so it's better
            }
            //            showSeat.setBooked(true); REDUNDANT
            showSeat.setBooking(booking);

        }

        // set the show
        booking.setShow(showSeats.get(0).getShow());
        // set the booking seats
        booking.setSeats(showSeats);
        // Set booking time
        booking.setBookingTime(LocalDateTime.now());
        // Set booking Status
        booking.setStatus(Booking.BookingStatus.PENDING); // SET IT PENDING FOR NOW as in it's pending payment

        showSeatRepository.saveAll(showSeats);
        bookingRepository.save(booking);

        List<String> seats = booking
                .getSeats()
                .stream()
                .map(showSeat->showSeat.getSeat().getSeatCategory()+"-"
                        +showSeat.getSeat().getRowNumber()+"-"
                        +showSeat.getSeat().getSeatNumber()).toList();

        bookingResponse.setBookingId(booking.getId());
        bookingResponse.setMovieName(booking.getShow().getMovie().getName());
        bookingResponse.setShowTime(booking.getShow().getStartTime());
        bookingResponse.setSeats(seats);
        bookingResponse.setBookingTime(booking.getBookingTime());
        bookingResponse.setBookingStatus(String.valueOf(booking.getStatus()));


        return bookingResponse;

    }



}

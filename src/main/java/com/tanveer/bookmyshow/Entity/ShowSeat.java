package com.tanveer.bookmyshow.Entity;


import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

// a show seat is create when there is a show



@Entity
@Table(
        indexes = {
                @Index(name="idx_showseat_show_id"  , columnList = "show_id"),
                @Index(name="idx_showseat_booking_id" , columnList = "booking_id")
        }
)
@Getter
@Setter
public class ShowSeat {

    //  PRODUCTION LOCKING SYSTEM IMPLEMENTAION :

    public enum ShowSeatStatus{
        AVAILABLE ,
        LOCKED , // currently user has selected the seat , it's locked for 10-115 minutes
        BOOKED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="show_id")
    private Show show;

    @ManyToOne
    @JoinColumn(name="seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name="booking_id")
    private Booking booking;

    /*
    “If we want traceability and reverse lookup,
     we should store booking reference inside ShowSeat;
     otherwise a simple boolean flag is sufficient for basic systems.”

     */

    @Enumerated(EnumType.STRING)
    private ShowSeatStatus status ;

    private LocalDateTime lockedAt; // store the time when it was locked so it can be made available after some time

//    private boolean isBooked; REDUNDANT , you can simply check if booking == null or not // BAD CODE PRACTICE // BASIC // LOCKING SEAT CANNOT BE IMPLEMENTED WITH THIS

}
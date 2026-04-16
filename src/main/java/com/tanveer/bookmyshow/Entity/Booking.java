package com.tanveer.bookmyshow.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
    indexes = {
        @Index(name="idx_booking_show_id" , columnList = "show_id")
    }
)
@Getter
@Setter
public class Booking {

    public enum BookingStatus {
        BOOKED,
        CANCELLED,
        PENDING,
        FAILED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="show_id")
    private Show show; // there can be many bookings for one show

//    @ManyToMany  BUG : WRONG : ManyToOne but not many to many , one showseat cannot belong to multiple bookings.
//    private List<ShowSeat> seats; //Many Booking can have many seats booked


    @OneToMany(mappedBy = "booking")
    private List<ShowSeat>  seats;

    @Enumerated(EnumType.STRING)
    private BookingStatus status; // BOOKED , CANCELLED

    // Add user

    // Many bookings can be mapped to single user

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    private LocalDateTime bookingTime;
}
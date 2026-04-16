package com.tanveer.bookmyshow.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Seat {
    public enum SeatCategory {
        SILVER,
        GOLD,
        PLATINUM
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;


    @Column(name = "row_num") // without this hibernate tries to create table with row_number which is a reserved keyword and the code breaks
    private int rowNumber;

    private int seatNumber;

    @Enumerated(EnumType.STRING) // without this annootaiton hibernate stores it as 0,1,2 values
    private SeatCategory seatCategory;

    @ManyToOne
    @JoinColumn(name="screen_id")
    private Screen screen;

}

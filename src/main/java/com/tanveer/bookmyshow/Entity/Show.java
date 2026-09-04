package com.tanveer.bookmyshow.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name="shows")
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie; // Many shows can be mapped to one movie

    @ManyToOne
    @JoinColumn(name="screen_id")
    private Screen screen; // Many shows can run on the same screen but at differnet times


    private LocalTime startTime;

    private LocalDate showDate;

    //   private Duration duration; not needed duration can be taken from movie

    //   private List<Integer> bookedSeats; wrong , Violates the normalization


}

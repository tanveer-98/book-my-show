package com.tanveer.bookmyshow.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(unique = true , nullable = false)
    private String name ;

    private String thumbnailUrl;

    private LocalDateTime releaseDate;

    private String genre;

    private int durationInMinutes;
}

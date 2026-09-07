package com.tanveer.bookmyshow.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

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

    @ManyToMany
    private Set<Genre> genres;

    private Integer durationInMinutes;
}

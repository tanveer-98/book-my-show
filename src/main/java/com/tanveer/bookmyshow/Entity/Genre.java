package com.tanveer.bookmyshow.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="genre")
public class Genre {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id ;

    private String name;
}

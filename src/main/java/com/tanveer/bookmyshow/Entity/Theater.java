package com.tanveer.bookmyshow.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    String address;

    @ManyToOne    // many theaters can belong to same city
    @JoinColumn(name="city_id")
    private City city;

    @OneToMany(mappedBy = "theater") ///
    private List<Screen> screens;

}

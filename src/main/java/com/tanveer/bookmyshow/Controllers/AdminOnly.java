package com.tanveer.bookmyshow.Controllers;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adminonly")
public class AdminOnly {


    @GetMapping("/getmovies")
    @PreAuthorize("hasRole('ADMIN')")
    public String getMovies(){
        return "movie details : ";
    }
}

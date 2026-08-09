package com.tanveer.bookmyshow.Controllers;

import com.tanveer.bookmyshow.Entity.Movie;
import com.tanveer.bookmyshow.Service.MovieService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
        // constructor injection
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return this.movieService.saveMovie(movie);
    }

    @PostMapping("/bulk")
    public List<Movie> createMovies(@RequestBody List<Movie> movies) {
        return this.movieService.saveAllMovies(movies);
    }



}

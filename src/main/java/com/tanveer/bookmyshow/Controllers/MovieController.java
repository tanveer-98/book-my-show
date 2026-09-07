package com.tanveer.bookmyshow.Controllers;

import com.tanveer.bookmyshow.Dto.MovieResponseDto;
import com.tanveer.bookmyshow.Entity.City;
import com.tanveer.bookmyshow.Entity.Movie;
import com.tanveer.bookmyshow.Service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService  movieService;

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

    @GetMapping
    public List<Movie> getMovies() {
        return movieService.getAllMovies();
    }


    @GetMapping("/city/{cityId}")
    public List<MovieResponseDto> getMoviesByCityId(@PathVariable("cityId") Long cityId){
        return movieService.getShowMoviesByCityId(cityId);
    }

}

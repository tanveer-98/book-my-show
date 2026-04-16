package com.tanveer.bookmyshow.Service;

import com.tanveer.bookmyshow.Repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    MovieRepository movieRepository;
    MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    
}

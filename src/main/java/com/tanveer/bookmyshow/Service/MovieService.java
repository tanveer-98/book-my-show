package com.tanveer.bookmyshow.Service;

import com.tanveer.bookmyshow.Entity.Movie;
import com.tanveer.bookmyshow.Repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    MovieRepository movieRepository;
    MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Optional<Movie> FindById(Long id) {
        return movieRepository.findById(id);
    }

    // create movie

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> saveAllMovies(List<Movie> movies) {
        return movieRepository.saveAll(movies);
    }

    public void deleteMovieById(Long  id) {
        try{
            movieRepository.deleteById(id);
        }
        catch(Exception e){
            throw new RuntimeException("Movie with id : "+ id+ "Does not exist");
        }
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }


    // get all movies by city Id

//    public List<Movie> getAllMoviesByCityId(Long cityId){
//        return movieRepository.
//    }



}

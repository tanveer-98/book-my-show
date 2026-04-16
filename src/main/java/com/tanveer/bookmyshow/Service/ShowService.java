package com.tanveer.bookmyshow.Service;

import com.tanveer.bookmyshow.Entity.Show;
import com.tanveer.bookmyshow.Repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {
// SERVICE USES THE REPOSITORY

    public final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }
    public List<Show> getShowByCity(Long cityId){
        return showRepository.findByScreen_Theater_City_Id(cityId);
    }

    public List<Show> getShowByMovieId ( Long movieId){
        return showRepository.findByMovieId(movieId);
    }

    public List<Show> getAllShows(){
        return showRepository.findAll();
    }

    public Show createShow(Show show){
        return showRepository.save(show);
    }
    public Show updateShow(Show show){
        return showRepository.save(show);
    }
}

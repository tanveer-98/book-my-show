package com.tanveer.bookmyshow.Controllers;

import com.tanveer.bookmyshow.Entity.Show;
import com.tanveer.bookmyshow.Service.ShowService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;// inject the showService
    }


    // now write the apis's the frontend will be using to fetch from the backend:

    // User Can get shows using two possible ways :
    // a. using show name
    // b. using city where they live


    // Get shows by cityId

    @GetMapping("/city/{cityId}")
    public List<Show> getShowsByCityId( @PathVariable Long cityId){
        return showService.getShowByCity(cityId);
    }

    // Get Shosw by movie Id :
    @GetMapping("/movie/{movieId}")
    public List<Show> getShowsByMovieId(@PathVariable Long movieId){
        return showService.getShowByMovieId(movieId);
    }

    // Just get all of them

    @GetMapping
    public List<Show> getAllShows(){
        return showService.getAllShows();
    }
}

package com.tanveer.bookmyshow.Controllers;

import com.tanveer.bookmyshow.Entity.City;
import com.tanveer.bookmyshow.Service.CityService;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    public City getCity(@PathVariable Long id) {
        return cityService.getCityById(id);
    }

    //  Get all cities to display or search
    @GetMapping
    public List<City> getCities() {
        return cityService.getAllCities();
    }

    @PostMapping
    public City createCity(@RequestBody City city){
        return  cityService.saveCity(city);
    }

    @PutMapping("/{id}")
    public City updateCity(@PathVariable Long id , @RequestBody City city){
        return cityService.saveCity(city);
    }

    @DeleteMapping("/{id}")
    public String deleteCity(@PathVariable Long id){
        cityService.deleteCityById(id);
        return "city Deleted Successfully";
    }


}

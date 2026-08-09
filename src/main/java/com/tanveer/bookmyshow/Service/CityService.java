package com.tanveer.bookmyshow.Service;

import com.tanveer.bookmyshow.Entity.City;
import com.tanveer.bookmyshow.Repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    public City saveCity(City city){
        return cityRepository.save(city);
    }

    public City getCityById(Long id){
        // if null is retured then error is thrown so better to keep it optional
        return cityRepository.findById(id).orElseThrow(()-> new RuntimeException("City not found with id : " + id));
    }

    //  BAD APPROACH , TWO DB QUERIES
//    public City getCityById2(Long id){
//        if(!cityRepository.existsById(id)){
//            throw new RuntimeException("City with id " + id + " does not exist");
//        }
//        return cityRepository.findById(id);
//    }

    public void deleteCityById(Long id){
        // take care of id

        try {
            cityRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("City with id " + id + " does not exist");
        }
    }
}

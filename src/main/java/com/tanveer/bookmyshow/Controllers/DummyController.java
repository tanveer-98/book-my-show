package com.tanveer.bookmyshow.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dummy")
public class DummyController {

    @GetMapping("/authTest")
    public String authTest(){
        return "Successfully Authenticated";
    }
}

package com.tanveer.bookmyshow;

import com.tanveer.bookmyshow.Controllers.MovieController;
import com.tanveer.bookmyshow.Controllers.TheaterController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookmyshowApplication {
//	MovieController movieController;
//	TheaterController theaterController; // springboot manages this


	public static void main(String[] args) {
		SpringApplication.run(BookmyshowApplication.class, args);

	}

}

package com.tanveer.bookmyshow.Repository;

import com.tanveer.bookmyshow.Entity.Show;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends BaseRepository<Show> {
    List<Show> findByMovieId(Long movieId);
//   Spring automatically writes the query :  SELECT * FROM show WHERE movie_id = ?;
    List<Show> findByScreen_Theater_City_Id(Long cityId);

    /*
        SELECT * FROM show
        JOIN screen
        JOIN theater
        WHERE city_id = ?
     */

//     AFTER creating the repository now you need to use it via service class.
    // controllers then will use your service class
}

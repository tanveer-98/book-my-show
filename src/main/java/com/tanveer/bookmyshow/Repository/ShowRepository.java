package com.tanveer.bookmyshow.Repository;

import com.tanveer.bookmyshow.Dto.ShowResponseDto;
import com.tanveer.bookmyshow.Entity.Show;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends BaseRepository<Show> {
    List<Show> findByMovieId(Long movieId);
//   Spring automatically writes the query :  SELECT * FROM show WHERE movie_id = ?;


    @Query(
            """ 
                select new com.tanveer.bookmyshow.Dto.ShowResponseDto(
                            s.id,
                            m.id,
                            m.name,
                            m.thumbnailUrl,
                            m.durationInMinutes,
                            t.id,
                            t.address ,
                            sc.id,
                            s.showDate,
                            s.startTime
                            )
               FROM Show s
               JOIN s.screen sc
               JOIN s.movie m
               JOIN sc.theater t
               WHERE t.city.id = :cityId
            """
    )
    List<ShowResponseDto> findShowsByCityId(@Param("cityId") Long cityId);


    @Query("""
          select new com.tanveer.bookmyshow.Dto.ShowResponseDto(
                            s.id,
                            m.id,
                            m.name,
                            m.thumbnailUrl,
                            m.durationInMinutes,
                            t.id,
                            t.address ,
                            sc.id,
                            s.showDate,
                            s.startTime
                            )
               FROM Show s
               JOIN s.screen sc
               JOIN s.movie m
               JOIN sc.theater t
               WHERE 1=1
               AND t.city.id = :cityId
               AND m.id = :movieId
               ORDER BY s.showDate
    """)
    List<ShowResponseDto> findShowsByMovieIdCityId(@Param("movieId") Long movieId ,   @Param("cityId") Long cityId);


    /*
        SELECT s.*
        FROM shows s
        JOIN screen sc ON s.screen_id = sc.id
        JOIN theater t ON sc.theater_id = t.id
        JOIN city c ON t.city_id = c.id
        WHERE c.id = ?
     */

//     AFTER creating the repository now you need to use it via service class.
    // controllers then will use your service class
}

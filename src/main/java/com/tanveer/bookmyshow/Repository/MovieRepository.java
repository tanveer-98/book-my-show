package com.tanveer.bookmyshow.Repository;

import com.tanveer.bookmyshow.Dto.MovieResponseDto;
import com.tanveer.bookmyshow.Entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends BaseRepository<Movie> {

    @Query("""
        SELECT DISTINCT new com.tanveer.bookmyshow.Dto.MovieResponseDto(
            m.id,
            m.durationInMinutes,
            m.name,
            m.releaseDate,
            m.thumbnailUrl
            )
        FROM Show s
        JOIN s.screen sc
        JOIN s.movie m
        JOIN sc.theater t
        WHERE t.city.id = :cityId
        ORDER BY m.releaseDate
    """)
    List<MovieResponseDto> getMoviesByCityId(@Param("cityId") Long cityId);
}

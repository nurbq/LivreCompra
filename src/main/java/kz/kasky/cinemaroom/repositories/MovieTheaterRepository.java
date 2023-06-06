package kz.kasky.cinemaroom.repositories;

import kz.kasky.cinemaroom.models.entities.MovieTheater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieTheaterRepository extends JpaRepository<MovieTheater, Integer> {


    @Query("SELECT m FROM MovieTheater m WHERE m.name LIKE CASCADE('%', :query, '%')")
    List<MovieTheater> searchMovieTheater(String query);
}

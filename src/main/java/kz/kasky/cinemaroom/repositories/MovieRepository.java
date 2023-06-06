package kz.kasky.cinemaroom.repositories;

import kz.kasky.cinemaroom.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("SELECT c FROM Movie c WHERE c.name LIKE CONCAT('%', :query, '%')")
    List<Movie> searchMovies(String query);
}

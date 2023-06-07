package kz.kasky.cinemaroom.repositories;

import kz.kasky.cinemaroom.models.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> getSchedulesByMovieId(Integer movieId);

    List<Schedule> getSchedulesByMovieTheaterId(Integer movieTheaterId);
}

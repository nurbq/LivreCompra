package kz.kasky.cinemaroom.integration;

import kz.kasky.cinemaroom.config.IntegrationTestContext;
import kz.kasky.cinemaroom.models.dto.ScheduleDto;
import kz.kasky.cinemaroom.models.entities.Movie;
import kz.kasky.cinemaroom.models.entities.MovieTheater;
import kz.kasky.cinemaroom.models.entities.Schedule;
import kz.kasky.cinemaroom.repositories.MovieRepository;
import kz.kasky.cinemaroom.repositories.MovieTheaterRepository;
import kz.kasky.cinemaroom.repositories.ScheduleRepository;
import kz.kasky.cinemaroom.services.ScheduleService;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ScheduleServiceIntegrationTest extends IntegrationTestContext {


    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieTheaterRepository movieTheaterRepository;

    @Autowired
    private ScheduleService scheduleService;


    @Test
    void createSchedule_withValidSchedule_returnsId() {
        Movie movie = getMovieAndSave();
        MovieTheater movieTheater = getMovieTheaterAndSave();

        ScheduleDto scheduleDto = new ScheduleDto(1, movie, movieTheater, LocalDateTime.now(), LocalDateTime.now().plusHours(1));

        Integer id = scheduleService.createSchedule(scheduleDto);

        Assertions.assertThat(id).isEqualTo(scheduleDto.getId());
    }

    @Test
    void getScheduleById_withValidId_returnsScheduleDto() {
        Schedule schedule = getScheduleAndSave();

        ScheduleDto result = scheduleService.getScheduleById(schedule.getId());

        Assertions.assertThat(result.getFinishTime()).isEqualTo(schedule.getFinishTime());
    }
    @Test
    void getScheduleByMovieId_withValidMovieId_returnsList() {
        Schedule schedule = getScheduleAndSave();

        List<ScheduleDto> result = scheduleService.getScheduleByMovieId(schedule.getMovie().getId());

        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).getFinishTime()).isEqualTo(schedule.getFinishTime());
    }

    @Test
    void getScheduleByMovieTheaterId_withValidMovieTheaterId_returnsList() {
        Schedule schedule = getScheduleAndSave();

        List<ScheduleDto> result = scheduleService.getScheduleByMovieId(schedule.getMovieTheater().getId());

        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).getFinishTime()).isEqualTo(schedule.getFinishTime());
    }

    private Schedule getScheduleAndSave() {
        Schedule schedule = new Schedule(1, getMovieAndSave(), getMovieTheaterAndSave(), LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        scheduleRepository.save(schedule);
        return schedule;
    }

    private MovieTheater getMovieTheaterAndSave() {
        MovieTheater movieTheater = new MovieTheater(1, "testName", "testAddress", 10, null);
        movieTheaterRepository.save(movieTheater);
        return movieTheater;
    }

    private Movie getMovieAndSave() {
        Movie movie = new Movie(1, "testName", "testDesc", "testGenre", null, null);
        movieRepository.save(movie);
        return movie;
    }
}

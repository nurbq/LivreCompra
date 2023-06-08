package kz.kasky.cinemaroom.services;


import kz.kasky.cinemaroom.models.dto.MovieDto;
import kz.kasky.cinemaroom.models.dto.ScheduleDto;
import kz.kasky.cinemaroom.models.entities.Movie;
import kz.kasky.cinemaroom.models.entities.MovieTheater;
import kz.kasky.cinemaroom.models.entities.Schedule;
import kz.kasky.cinemaroom.repositories.MovieRepository;
import kz.kasky.cinemaroom.repositories.MovieTheaterRepository;
import kz.kasky.cinemaroom.repositories.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {


    private final ScheduleRepository scheduleRepository;
    private final MovieRepository movieRepository;
    private final MovieTheaterRepository movieTheaterRepository;


    public ScheduleService(ScheduleRepository scheduleRepository, MovieRepository movieRepository, MovieTheaterRepository movieTheaterRepository) {
        this.scheduleRepository = scheduleRepository;
        this.movieRepository = movieRepository;
        this.movieTheaterRepository = movieTheaterRepository;
    }

    public List<ScheduleDto> getAllSchedules() {

        return scheduleRepository.findAll().stream()
                .map(this::mapToScheduleDto)
                .toList();
    }


    public void createSchedule(ScheduleDto scheduleDto) {

        Optional<MovieTheater> movieTheater = movieTheaterRepository.findById(scheduleDto.getMovieTheater().getId());
        Optional<Movie> movie = movieRepository.findById(scheduleDto.getMovie().getId());


        if (movieTheater.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Movie Theater not found"
            );
        }

        if (movie.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Movie not found"
            );
        }

        Schedule schedule = mapToSchedule(scheduleDto);


        scheduleRepository.save(schedule);

    }


    public ScheduleDto getScheduleById(Integer id) {
        return mapToScheduleDto(scheduleRepository.findById(id).get());
    }

    public List<ScheduleDto> getScheduleByMovieId(Integer movieId) {
        return scheduleRepository.getSchedulesByMovieId(movieId).stream()
                .map(this::mapToScheduleDto)
                .toList();
    }

    public List<ScheduleDto> getScheduleByMovieTheaterId(Integer movieTheaterId) {
        return scheduleRepository.getSchedulesByMovieTheaterId(movieTheaterId).stream()
                .map(this::mapToScheduleDto)
                .toList();
    }


    private ScheduleDto mapToScheduleDto(Schedule schedule) {

        return ScheduleDto.builder()
                .id(schedule.getId())
                .movie(schedule.getMovie())
                .movieTheater(schedule.getMovieTheater())
                .startTime(schedule.getStartTime())
                .finishTime(schedule.getFinishTime())
                .build();
    }


    private Schedule mapToSchedule(ScheduleDto scheduleDto) {


        return Schedule.builder()
                .id(scheduleDto.getId())
                .movie(scheduleDto.getMovie())
                .movieTheater(scheduleDto.getMovieTheater())
                .startTime(scheduleDto.getStartTime())
                .finishTime(scheduleDto.getFinishTime())
                .build();
    }


}

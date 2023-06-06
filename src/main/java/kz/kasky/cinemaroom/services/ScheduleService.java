package kz.kasky.cinemaroom.services;


import kz.kasky.cinemaroom.models.dto.ScheduleDto;
import kz.kasky.cinemaroom.models.entities.Movie;
import kz.kasky.cinemaroom.models.entities.Schedule;
import kz.kasky.cinemaroom.repositories.MovieRepository;
import kz.kasky.cinemaroom.repositories.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {


    private final ScheduleRepository scheduleRepository;
    private final MovieRepository movieRepository;


    public ScheduleService(ScheduleRepository scheduleRepository, MovieRepository movieRepository) {
        this.scheduleRepository = scheduleRepository;
        this.movieRepository = movieRepository;
    }


    public void createSchedule(Integer movieId, ScheduleDto scheduleDto) {

        Movie movie = movieRepository.findById(movieId).get();

        Schedule schedule = mapToSchedule(scheduleDto);


        schedule.setMovie(movie);

        scheduleRepository.save(schedule);

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

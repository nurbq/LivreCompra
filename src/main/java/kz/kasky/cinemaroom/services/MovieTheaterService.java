package kz.kasky.cinemaroom.services;


import kz.kasky.cinemaroom.models.dto.MovieTheaterDto;
import kz.kasky.cinemaroom.models.entities.MovieTheater;
import kz.kasky.cinemaroom.repositories.MovieTheaterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieTheaterService {

    private final MovieTheaterRepository movieTheaterRepository;


    public MovieTheaterService(MovieTheaterRepository movieTheaterRepository) {
        this.movieTheaterRepository = movieTheaterRepository;
    }


    public List<MovieTheaterDto> getAllMovieTheater() {

        Iterable<MovieTheaterDto> movieTheaters = movieTheaterRepository.findAll()
                .stream()
                .map(this::mapToMovieTheaterDto)
                .collect(Collectors.toList());

        List<MovieTheaterDto> movieTheaterDtoList = new ArrayList<>();

        movieTheaters.forEach(movieTheaterDtoList::add);

        movieTheaterDtoList.sort(Comparator.comparing(MovieTheaterDto::getName));


        return movieTheaterDtoList;
    }



    public MovieTheaterDto getMovieTheaterById(Integer id) {


        Optional<MovieTheater> movieTheater = movieTheaterRepository.findById(id);


        if (movieTheater.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }


        return mapToMovieTheaterDto(movieTheater.get());
    }


    public void saveMovie(MovieTheaterDto movieTheaterDto) {
        MovieTheater movieTheater = mapToMovieTheater(movieTheaterDto);

        movieTheaterRepository.save(movieTheater);
    }

    public void updateMovie(Integer id, MovieTheaterDto movieTheaterDto) {
        MovieTheater movieTheater = movieTheaterRepository.findById(id).get();

        movieTheater.setName(movieTheaterDto.getName());
        movieTheater.setAddress(movieTheaterDto.getAddress());
        movieTheater.setMaxSeats(movieTheaterDto.getMaxSeats());

        movieTheaterRepository.save(movieTheater);
    }




    public void deleteMovie(Integer id) {
        movieTheaterRepository.deleteById(id);
    }

    public List<MovieTheaterDto> searchMovies(String query) {
        List<MovieTheater> movieTheaters = movieTheaterRepository.searchMovieTheater(query);

        return movieTheaters.stream()
                .map(this::mapToMovieTheaterDto)
                .collect(Collectors.toList());
    }





    private MovieTheaterDto mapToMovieTheaterDto(MovieTheater movieTheater) {


        return MovieTheaterDto.builder()
                .id(movieTheater.getId())
                .name(movieTheater.getName())
                .address(movieTheater.getAddress())
                .maxSeats(movieTheater.getMaxSeats())
                .movies(movieTheater.getMovies())
                .build();
    }


    private MovieTheater mapToMovieTheater(MovieTheaterDto movieTheaterDto) {


        return MovieTheater.builder()
                .id(movieTheaterDto.getId())
                .name(movieTheaterDto.getName())
                .address(movieTheaterDto.getAddress())
                .maxSeats(movieTheaterDto.getMaxSeats())
                .movies(movieTheaterDto.getMovies())
                .build();
    }
}

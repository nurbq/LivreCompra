package kz.kasky.cinemaroom.services;


import kz.kasky.cinemaroom.models.dto.MovieDto;
import kz.kasky.cinemaroom.models.entities.Movie;
import kz.kasky.cinemaroom.repositories.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieService {

    private MovieRepository movieRepository;

    public List<MovieDto> getAllMovie() {

        return movieRepository.findAll()
                .stream()
                .map(this::mapToMovieDto)
                .sorted(Comparator.comparing(MovieDto::getName))
                .toList();
    }

    public MovieDto getMovieById(Integer id) {
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }
        return mapToMovieDto(movie.get());
    }


    public Integer saveMovie(MovieDto movieDto) {
        Movie movie = mapToMovie(movieDto);

        return movieRepository.save(movie).getId();
    }

    public String updateMovie(Integer id, MovieDto movieDto) {
        Movie movie = movieRepository.findById(id).get();

        movie.setName(movieDto.getName());
        movie.setGenre(movieDto.getGenre());
        movie.setDescription(movieDto.getDescription());

        String name = movieRepository.save(movie).getName();
        return name;
    }


    public void deleteMovie(Integer id) {
        movieRepository.deleteById(id);
    }

    public List<MovieDto> searchMovies(String query) {
        List<Movie> movies = movieRepository.searchMovies(query);

        return movies.stream()
                .map(this::mapToMovieDto)
                .collect(Collectors.toList());
    }


    private Movie mapToMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setName(movieDto.getName());
        movie.setGenre(movieDto.getGenre());
        movie.setDescription(movieDto.getDescription());
        movie.setMovieDetail(movieDto.getMovieDetail());
        movie.setMovieTheaters(movieDto.getMovieTheaters());

        return movie;
    }


    private MovieDto mapToMovieDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .genre(movie.getGenre())
                .description(movie.getDescription())
                .movieDetail(movie.getMovieDetail())
                .movieTheaters(movie.getMovieTheaters())
                .build();
    }


}

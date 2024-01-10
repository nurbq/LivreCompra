package kz.kasky.cinemaroom.unit;

import kz.kasky.cinemaroom.models.dto.MovieDto;
import kz.kasky.cinemaroom.models.entities.Movie;
import kz.kasky.cinemaroom.models.entities.MovieDetail;
import kz.kasky.cinemaroom.repositories.MovieRepository;
import kz.kasky.cinemaroom.services.MovieService;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {


    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void getMovieById_withValidId_returnsMovie() {
        Movie movie = getMockMovie();

        when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));

        MovieDto result = movieService.getMovieById(movie.getId());

        Assertions.assertThat(result.getName()).isEqualTo(movie.getName());
    }

    @Test
    void searchMovies() {
        int size = 5;
        List<Movie> movies = getMockMovies(size);
        when(movieRepository.searchMovies(anyString())).thenReturn(movies);

        List<MovieDto> result = movieService.searchMovies("asd");

        Assertions.assertThat(result.size()).isEqualTo(size);
    }

    private List<Movie> getMockMovies(int size) {
        List<Movie> movies = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            movies.add(new Movie(i, "name" + i, "description" + i, "genre" + i, new MovieDetail(), null));
        }

        return movies;
    }

    private Movie getMockMovie() {
        return new Movie(1, "name", "description", "genre", new MovieDetail(), null);
    }
}
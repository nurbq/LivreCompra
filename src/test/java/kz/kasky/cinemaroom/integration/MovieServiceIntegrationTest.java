package kz.kasky.cinemaroom.integration;


import kz.kasky.cinemaroom.config.IntegrationTestContext;
import kz.kasky.cinemaroom.models.dto.MovieDto;
import kz.kasky.cinemaroom.models.entities.Movie;
import kz.kasky.cinemaroom.repositories.MovieRepository;
import kz.kasky.cinemaroom.services.MovieService;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Isolated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Isolated
public class MovieServiceIntegrationTest extends IntegrationTestContext {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @AfterEach
    void setUp() {
        movieRepository.deleteAll();
    }

    @Test
    public void getAllMovie_returnsMoveDtos() {
        int size = 3;
        for (int i = 1; i <= size; i++) {
            movieRepository.save(new Movie(i, "name" + i, "desc" + i, "genre" + i, null, null));
        }

        List<MovieDto> result = movieService.getAllMovie();

        Assertions.assertThat(result.size()).isEqualTo(size);
    }

    @Test
    public void getMovieById_withValidId_returnsMovieDto() {
        Movie movie = getMockMovie();
        movieRepository.save(movie);

        MovieDto movieDto = movieService.getMovieById(movie.getId());

        Assertions.assertThat(movieDto.getName()).isEqualTo(movie.getName());
    }

    @Test
    public void getMovieById_movieNotFound_throwsException() {
        Assertions.assertThatThrownBy(() ->
                        movieService.getMovieById(1))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("not found");
    }

    @Test
    public void saveMovie_withValidMovieDto() {
        MovieDto movieDto = getMockMovieDto();

        Integer result = movieService.saveMovie(movieDto);

        Assertions.assertThat(result).isEqualTo(movieDto.getId());
    }

    @Test
    void updateMovie_withValidParameters_returnsName() {
        MovieDto movieDto = getMockMovieDto();
        Movie movie = getMockMovie();
        movieRepository.save(movie);

        String name = movieService.updateMovie(movieDto.getId(), movieDto);

        Assertions.assertThat(name).isEqualTo(movieDto.getName());
    }

    @Test
    void deleteMovie_withValidId() {
        Movie movie = getMockMovie();
        movieRepository.save(movie);

        movieService.deleteMovie(movie.getId());

        Movie movieFromDb = movieRepository.findById(movie.getId()).orElse(null);

        Assertions.assertThat(movieFromDb).isNull();
    }

    @Test
    void searchMovies() {
        int size = 3;
        for (int i = 1; i <= size; i++) {
            movieRepository.save(new Movie(i, "name" + i, "desc" + i, "genre" + i, null, null));
        }
        List<MovieDto> result = movieService.searchMovies("name1");

        Assertions.assertThat(result.size()).isEqualTo(1);
    }

    private MovieDto getMockMovieDto() {
        return new MovieDto(1, "testName", "testGenre", "testDescription", null, null);
    }

    private Movie getMockMovie() {
        return new Movie(1, "testname", "testDescription", "testGenre", null, null);
    }
}

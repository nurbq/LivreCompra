package kz.kasky.cinemaroom.integration;

import kz.kasky.cinemaroom.config.IntegrationTestContext;
import kz.kasky.cinemaroom.models.dto.MovieTheaterDto;
import kz.kasky.cinemaroom.models.entities.MovieTheater;
import kz.kasky.cinemaroom.repositories.MovieTheaterRepository;
import kz.kasky.cinemaroom.services.MovieTheaterService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class MovieTheaterServiceIntegrationTest extends IntegrationTestContext {

    @Autowired
    private MovieTheaterRepository movieTheaterRepository;

    @Autowired
    private MovieTheaterService movieTheaterService;

    @AfterEach
    void setUp() {
        movieTheaterRepository.deleteAll();
    }

    @Test
    void getAllMovieTheater_returnsList() {
        int size = 5;
        for (int i = 1; i <= size; i++) {
            movieTheaterRepository.save(new MovieTheater(i, "name" + i, "address" + i, i, null));
        }

        List<MovieTheaterDto> movieTheaterDtoList = movieTheaterService.getAllMovieTheater();

        Assertions.assertThat(movieTheaterDtoList.size()).isEqualTo(size);
    }

    @Test
    void getMovieTheaterById_withValidId_returnsMovieTheaterDto() {
        MovieTheater movieTheater = getMockMovieTheater();
        movieTheaterRepository.save(movieTheater);

        MovieTheaterDto result = movieTheaterService.getMovieTheaterById(movieTheater.getId());

        Assertions.assertThat(result.getName()).isEqualTo(movieTheater.getName());
    }


    @Test
    void updateMovie_withValidParameters_returnsName() {
        MovieTheater movieTheater = getMockMovieTheater();
        movieTheaterRepository.save(movieTheater);
        MovieTheaterDto movieTheaterDto = new MovieTheaterDto(1, "updatedName", "updatedAddress", 2, null);

        String name = movieTheaterService.updateMovie(movieTheaterDto.getId(), movieTheaterDto);

        Assertions.assertThat(name).isEqualTo(movieTheaterDto.getName());
    }


    @Test
    void deleteMovie_withValidId_successfullyDeletes() {
        MovieTheater movieTheater = getMockMovieTheater();
        movieTheaterRepository.save(movieTheater);

        movieTheaterService.deleteMovie(movieTheater.getId());

        MovieTheater movieTheaterFromDb = movieTheaterRepository.findById(movieTheater.getId()).orElse(null);
        Assertions.assertThat(movieTheaterFromDb).isNull();
    }

    private MovieTheater getMockMovieTheater() {
        return new MovieTheater(1, "testName", "testAddress", 1, null);
    }
}

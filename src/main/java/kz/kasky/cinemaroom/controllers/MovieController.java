package kz.kasky.cinemaroom.controllers;


import jakarta.validation.Valid;
import kz.kasky.cinemaroom.models.dto.MovieDto;
import kz.kasky.cinemaroom.models.dto.ScheduleDto;
import kz.kasky.cinemaroom.models.entities.Movie;
import kz.kasky.cinemaroom.services.MovieService;
import kz.kasky.cinemaroom.services.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final ScheduleService scheduleService;

    public MovieController(MovieService movieService, ScheduleService scheduleService) {
        this.movieService = movieService;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/movies")
    public String getAllMovies(Model model) {
        List<MovieDto> moviesList = movieService.getAllMovie();

        model.addAttribute("movies", moviesList);
        model.addAttribute("module", "movies");

        return "movie_page/movies";
    }

    @GetMapping(path = "/movies/{id}")
    public String getMovie(@PathVariable("id") Integer id, Model model) {
        MovieDto movie = movieService.getMovieById(id);
        List<ScheduleDto> scheduleDtos = scheduleService.getScheduleByMovieId(id);

        model.addAttribute("movie", movie);
        model.addAttribute("schedules", scheduleDtos);

        return "movie_page/detailed_movie";
    }


    @GetMapping("/movies/form")
    public String createMovieForm(Model model) {
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "movie_page/movies_create";
    }

    @PostMapping("/movies/save")
    public String saveMovie(@Valid @ModelAttribute("movie") MovieDto movieDto,
                            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("movie", movieDto);
            return "movie_page/movies_create";
        }

        Integer id = movieService.saveMovie(movieDto);
        return "redirect:/movies";
    }

    @GetMapping("/movies/{movieId}/edit")
    public String editMovieForm(@PathVariable("movieId") Integer id, Model model) {
        MovieDto movie = movieService.getMovieById(id);

        model.addAttribute("movie", movie);

        return "movie_page/movies_edit";
    }

    @PostMapping("/movies/{movieId}/edit")
    public String updateMovie(@PathVariable("movieId") Integer id,
                              @Valid @ModelAttribute("movie") MovieDto movieDto,
                              BindingResult result) {

        if (result.hasErrors()) {
            return "movie_page/movies_edit";
        }

        String name  = movieService.updateMovie(id, movieDto);

        return "redirect:/movies";
    }


    @GetMapping("/movies/{movieId}/delete")
    public String deleteMovieForm(@PathVariable("movieId") Integer id) {
        movieService.deleteMovie(id);

        return "redirect:/movies";
    }

    @GetMapping("/movies/search")
    public String searchMovie(@RequestParam(value = "query") String query, Model model) {
        List<MovieDto> movieDtos = movieService.searchMovies(query);

        model.addAttribute("movies", movieDtos);
        return "movie_page/movies";
    }
}

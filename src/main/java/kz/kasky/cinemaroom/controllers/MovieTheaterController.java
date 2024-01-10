package kz.kasky.cinemaroom.controllers;


import jakarta.validation.Valid;
import kz.kasky.cinemaroom.models.dto.MovieTheaterDto;
import kz.kasky.cinemaroom.models.dto.ScheduleDto;
import kz.kasky.cinemaroom.models.entities.MovieTheater;
import kz.kasky.cinemaroom.services.MovieTheaterService;
import kz.kasky.cinemaroom.services.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movieTheatres")
public class MovieTheaterController {

    private final MovieTheaterService movieTheaterService;
    private final ScheduleService scheduleService;

    public MovieTheaterController(MovieTheaterService movieTheaterService, ScheduleService scheduleService) {
        this.movieTheaterService = movieTheaterService;
        this.scheduleService = scheduleService;
    }


    @GetMapping
    public String getAllMovieTheatres(Model model) {
        List<MovieTheaterDto> movieTheaterDtoList = movieTheaterService.getAllMovieTheater();

        model.addAttribute("movieTheatres", movieTheaterDtoList);

        return "movie_theatres_page/movie-theatres-list";
    }



    @GetMapping(path = "/{id}")
    public String getMovieTheaterById(@PathVariable("id") Integer id, Model model) {
        MovieTheaterDto movieTheaterDto = movieTheaterService.getMovieTheaterById(id);
        List<ScheduleDto> scheduleDtos = scheduleService.getScheduleByMovieTheaterId(id);

        model.addAttribute("movieTheater", movieTheaterDto);
        model.addAttribute("schedules", scheduleDtos);

        return "movie_theatres_page/detailed-movie-theater";
    }


    @GetMapping("/form")
    public String createMovieTheaterForm(Model model) {
        MovieTheater movieTheater = new MovieTheater();
        model.addAttribute("movieTheater", movieTheater);
        return "movie_theatres_page/movie-theatres-create";
    }

    @PostMapping("/save")
    public String saveMovie(@Valid @ModelAttribute("movieTheater") MovieTheaterDto movieTheaterDto,
                            BindingResult bindingResult, Model model) {



        if (bindingResult.hasErrors()) {
            model.addAttribute("movieTheater", movieTheaterDto);
            return "movie_theatres_page/movie-theatres-create";
        }

        movieTheaterService.saveMovie(movieTheaterDto);
        return "redirect:/movieTheatres";
    }

    @GetMapping("/{movieTheaterId}/edit")
    public String editMovieForm(@PathVariable("movieTheaterId") Integer id, Model model) {
        MovieTheaterDto movieTheaterDto = movieTheaterService.getMovieTheaterById(id);

        model.addAttribute("movieTheater", movieTheaterDto);

        return "movie_theatres_page/movie-theatres-edit";
    }

    @PostMapping("/{movieTheaterId}/edit")
    public String updateMovie(@PathVariable("movieTheaterId") Integer id,
                              @Valid @ModelAttribute("movie") MovieTheaterDto movieTheaterDto,
                              BindingResult result) {

        if (result.hasErrors()) {
            return "movie_theatres_page/movie-theatres-edit";
        }

        String name = movieTheaterService.updateMovie(id, movieTheaterDto);

        return "redirect:/movieTheatres";
    }

    @GetMapping("/{movieTheaterId}/delete")
    public String deleteMovieForm(@PathVariable("movieTheaterId") Integer id) {
        movieTheaterService.deleteMovie(id);

        return "redirect:/movieTheatres";
    }


}

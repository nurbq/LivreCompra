package kz.kasky.cinemaroom.controllers;


import jakarta.validation.Valid;
import kz.kasky.cinemaroom.models.dto.MovieDto;
import kz.kasky.cinemaroom.models.dto.MovieTheaterDto;
import kz.kasky.cinemaroom.models.dto.ScheduleDto;
import kz.kasky.cinemaroom.models.dto.TicketDto;
import kz.kasky.cinemaroom.services.MovieService;
import kz.kasky.cinemaroom.services.MovieTheaterService;
import kz.kasky.cinemaroom.services.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final MovieService movieService;
    private final MovieTheaterService movieTheaterService;


    public ScheduleController(ScheduleService scheduleService, MovieService movieService, MovieTheaterService movieTheaterService) {
        this.scheduleService = scheduleService;
        this.movieService = movieService;
        this.movieTheaterService = movieTheaterService;
    }



    @GetMapping("/create")
    public String createScheduleForm(Model model) {
        List<MovieDto> movieDtoList = movieService.getAllMovie();
        List<MovieTheaterDto> movieTheaterDtoList = movieTheaterService.getAllMovieTheater();

        model.addAttribute("movies", movieDtoList);
        model.addAttribute("movieTheatres", movieTheaterDtoList);
        model.addAttribute("schedule", new ScheduleDto());


        return "schedules_page/schedule-create";
    }


    @PostMapping("/create")
    public String saveTicket(@Valid @ModelAttribute("schedule") ScheduleDto ticketDto,
                             BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ticket", ticketDto);
            return "ticket_page/ticket_create";
        }




        return "redirect:/tickets";
    }
}

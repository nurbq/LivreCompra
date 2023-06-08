package kz.kasky.cinemaroom.controllers;


import jakarta.validation.Valid;
import kz.kasky.cinemaroom.models.dto.MovieDto;
import kz.kasky.cinemaroom.models.dto.ScheduleDto;
import kz.kasky.cinemaroom.models.dto.TicketDto;
import kz.kasky.cinemaroom.services.MovieService;
import kz.kasky.cinemaroom.services.OrderService;
import kz.kasky.cinemaroom.services.ScheduleService;
import kz.kasky.cinemaroom.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final ScheduleService scheduleService;
    private final MovieService movieService;
    private final OrderService orderService;



    public TicketController(TicketService ticketService, ScheduleService scheduleService, MovieService movieService, OrderService orderService) {
        this.ticketService = ticketService;
        this.scheduleService = scheduleService;
        this.movieService = movieService;
        this.orderService = orderService;
    }


    @GetMapping
    public String getAllTickets(Model model) {
        List<TicketDto> ticketDtoList = ticketService.getAllTickets();

        model.addAttribute("tickets", ticketDtoList);

        return "ticket_page/tickets-list";
    }


    @GetMapping("/{movieId}/new")
    public String createTicketForm(@PathVariable("movieId") Integer clubId, Model model) {
        TicketDto ticketDto = new TicketDto();

        model.addAttribute("clubId", clubId);
        model.addAttribute("ticket", ticketDto);

        return "ticket_page/tickets-create";
    }


    @PostMapping("/new")
    public String saveTicket(@Valid @ModelAttribute("ticket") TicketDto ticketDto,
                             BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("ticket", ticketDto);
            return "ticket_page/ticket_create";
        }

        ticketService.saveTicket(ticketDto);


        return "redirect:/tickets";
    }


    @GetMapping("/movie/{movieId}/schedule/{scheduleId}/order")
    public String orderTicketForm(@PathVariable("movieId") Integer movieId,
                                  @PathVariable("scheduleId") Integer scheduleId,
                                  Model model) {


        TicketDto ticketDto = new TicketDto();
        ScheduleDto scheduleDto = scheduleService.getScheduleById(scheduleId);
        MovieDto movie = movieService.getMovieById(movieId);


        model.addAttribute("schedule", scheduleDto);
        model.addAttribute("ticket", ticketDto);
        model.addAttribute("movie", movie);

        return "ticket_page/ticket-order";
    }

    @PostMapping("/movie/{movieId}/schedule/{scheduleId}/order")
    public String orderTicket(@PathVariable("movieId") Integer movieId,
                              @PathVariable("scheduleId") Integer scheduleId,
                              @ModelAttribute("ticket") TicketDto ticketDto,
                              Model model) {

        ticketService.createTicket(movieId, scheduleId, ticketDto);


        return "redirect:/movies";
    }
}

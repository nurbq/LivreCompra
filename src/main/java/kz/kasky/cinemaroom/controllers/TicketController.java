//package kz.kasky.cinemaroom.controllers;
//
//
//import jakarta.validation.Valid;
//import kz.kasky.cinemaroom.models.dto.TicketDto;
//import kz.kasky.cinemaroom.services.TicketService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/tickets")
//public class TicketController {
//
//    private final TicketService ticketService;
//
//
//    public TicketController(TicketService ticketService) {
//        this.ticketService = ticketService;
//    }
//
//
//    @GetMapping
//    public String getAllTickets(Model model) {
//        List<TicketDto> ticketDtoList = ticketService.getAllTickets();
//
//        model.addAttribute("tickets", ticketDtoList);
//
//        return "ticket_page/tickets-list";
//    }
//
//
//    @GetMapping("/{movieId}/new")
//    public String createTicketForm(@PathVariable("movieId") Integer clubId, Model model) {
//        TicketDto ticketDto = new TicketDto();
//
//        model.addAttribute("clubId", clubId);
//        model.addAttribute("ticket", ticketDto);
//
//        return "ticket_page/tickets-create";
//    }
//
//
//    @PostMapping("/new")
//    public String saveTicket(@Valid @ModelAttribute("ticket") TicketDto ticketDto,
//                             BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("ticket", ticketDto);
//            return "ticket_page/ticket_create";
//        }
//
//        ticketService.saveTicket(ticketDto);
//
//
//        return "redirect:/tickets";
//    }
//}

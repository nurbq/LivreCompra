package kz.kasky.cinemaroom.services;


import kz.kasky.cinemaroom.models.dto.TicketDto;
import kz.kasky.cinemaroom.models.entities.Schedule;
import kz.kasky.cinemaroom.models.entities.Ticket;
import kz.kasky.cinemaroom.repositories.MovieRepository;
import kz.kasky.cinemaroom.repositories.ScheduleRepository;
import kz.kasky.cinemaroom.repositories.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;
    private final ScheduleRepository scheduleRepository;


    public TicketService(TicketRepository ticketRepository, MovieRepository movieRepository, ScheduleRepository scheduleRepository) {
        this.ticketRepository = ticketRepository;
        this.movieRepository = movieRepository;
        this.scheduleRepository = scheduleRepository;
    }




    public List<TicketDto> getAllTickets() {


        return ticketRepository.findAll().stream()
                .map(this::mapToTicketDto)
                .collect(Collectors.toList());
    }


    public TicketDto getTicketById(Integer id) {

        Optional<Ticket> ticket = ticketRepository.findById(id);


        if (ticket.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "not found"
            );
        }


        return mapToTicketDto(ticket.get());
    }

    public void saveTicket(TicketDto ticketDto) {
        Ticket ticket = mapToTicket(ticketDto);

        ticketRepository.save(ticket);
    }


    private TicketDto mapToTicketDto(Ticket ticket) {


        return TicketDto.builder()
                .id(ticket.getId())
                .schedule(ticket.getSchedule())
                .spot(ticket.getSpot())
                .isPurchased(ticket.getIsPurchased())
                .build();
    }

    private Ticket mapToTicket(TicketDto ticketDto) {

        return Ticket.builder()
                .id(ticketDto.getId())
                .schedule(ticketDto.getSchedule())
                .spot(ticketDto.getSpot())
                .isPurchased(ticketDto.getIsPurchased())
                .build();
    }

    public void createTicket(Integer movieId, Integer scheduleId, TicketDto ticketDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();

        Ticket ticket = new Ticket();



    }
}

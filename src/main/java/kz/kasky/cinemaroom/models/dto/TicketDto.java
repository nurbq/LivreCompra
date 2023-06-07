package kz.kasky.cinemaroom.models.dto;


import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kz.kasky.cinemaroom.models.entities.Movie;
import kz.kasky.cinemaroom.models.entities.Order;
import kz.kasky.cinemaroom.models.entities.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {


    private Integer id;

    private Order order;

    private Schedule schedule;

    private Integer spot;

    private Boolean isPurchased;

}

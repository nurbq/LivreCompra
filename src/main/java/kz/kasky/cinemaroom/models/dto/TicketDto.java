package kz.kasky.cinemaroom.models.dto;


import kz.kasky.cinemaroom.models.entities.Movie;
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

    private Movie movie;

    private Integer orderId;

    private Integer theaterId;

    private Integer spot;

    private Boolean isPurchased;

}

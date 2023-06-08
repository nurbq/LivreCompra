package kz.kasky.cinemaroom.models.dto;


import kz.kasky.cinemaroom.models.entities.Ticket;
import kz.kasky.cinemaroom.models.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer id;

    private UserEntity userEntity;

    private LocalDateTime orderTime;

    private List<Ticket> tickets;
}

package kz.kasky.cinemaroom.models.dto;


import jakarta.validation.constraints.*;
import kz.kasky.cinemaroom.models.entities.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieTheaterDto {


    private Integer id;

    @NotBlank(message = "Name should not be empty")
    @Size(
            min = 5,
            max = 100,
            message = "Should not be so short and long"
    )
    private String name;

    private String address;

    @NotNull(message = "Required and should be positive number")
    @Min(value = 10, message = "Should be positive number and not less than 10")
    @Max(value = 250, message = "No more than 250")
    @Digits(integer = 3, fraction = 0, message = "Value must be an integer")
    private Integer maxSeats;

    private Set<Schedule> movies;


}

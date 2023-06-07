package kz.kasky.cinemaroom.models.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kz.kasky.cinemaroom.models.entities.MovieDetail;
import kz.kasky.cinemaroom.models.entities.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private Integer id;
    @NotBlank(message = "Required")
    private String name;
    @NotBlank(message = "Genre should not be empty")
    private String genre;
    @Size(
            min = 2,
            max = 255,
            message = "Description should not be empty")
    private String description;

    private MovieDetail movieDetail;

    private Set<Schedule> movieTheaters;
}

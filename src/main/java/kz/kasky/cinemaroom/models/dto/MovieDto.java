package kz.kasky.cinemaroom.models.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

package kz.kasky.cinemaroom.models.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {

    private Integer id;
    @NotEmpty
    @NotBlank

    private String userName;
    @NotEmpty
    @NotBlank
    private String password;
}

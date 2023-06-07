package kz.kasky.cinemaroom.models.dto;


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
    private String userName;
    @NotEmpty
    private String password;
}

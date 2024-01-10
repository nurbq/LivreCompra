package kz.kasky.cinemaroom.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "movie_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer duration;

    @Column(name = "premiere_date")
    private LocalDateTime premiereDate;

    private String director;


    @OneToOne(mappedBy = "movieDetail")
    private Movie movie;
}

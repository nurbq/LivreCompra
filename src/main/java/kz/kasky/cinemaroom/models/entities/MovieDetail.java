package kz.kasky.cinemaroom.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movie_detail")
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movieDetail) {
        this.movie = movieDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(LocalDateTime premiereDate) {
        this.premiereDate = premiereDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}

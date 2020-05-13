package nl.robinlaugs.kpnmovies.controller.dto;

import lombok.Getter;
import nl.robinlaugs.kpnmovies.model.Movie;

@Getter
public class MovieDto {

    private final String title;
    private final String imdb;

    public MovieDto(Movie movie) {
        this.title = movie.getTitle();
        this.imdb = movie.getImdb();
    }

}

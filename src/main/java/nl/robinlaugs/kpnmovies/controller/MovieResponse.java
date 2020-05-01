package nl.robinlaugs.kpnmovies.controller;

import lombok.Getter;
import lombok.Setter;
import nl.robinlaugs.kpnmovies.domain.Movie;

@Getter
@Setter
public class MovieResponse {

    private String title;
    private String imdb;

    public MovieResponse(Movie movie) {
        this.title = movie.getTitle();
        this.imdb = movie.getImdb();
    }

}

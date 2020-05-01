package nl.robinlaugs.kpnmovies.batch.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MovieDto {

    private String title;
    private double rating;
    private int runtime;
    private String imdb;
    private List<String> genres;
    private List<ActorDto> actors;

}

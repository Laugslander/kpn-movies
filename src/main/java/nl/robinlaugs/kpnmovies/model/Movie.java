package nl.robinlaugs.kpnmovies.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.util.List;

@Data
@Builder
@Document(collection = "movies")
public class Movie {

    @Id
    private String id;

    private final String title;
    private final double rating;
    private final Duration runtime;
    private final String imdb;
    private final List<Genre> genres;
    private final List<Actor> actors;

}

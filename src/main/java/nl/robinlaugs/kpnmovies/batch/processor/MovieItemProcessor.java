package nl.robinlaugs.kpnmovies.batch.processor;

import nl.robinlaugs.kpnmovies.batch.dto.ActorDto;
import nl.robinlaugs.kpnmovies.batch.dto.MovieDto;
import nl.robinlaugs.kpnmovies.domain.Actor;
import nl.robinlaugs.kpnmovies.domain.Gender;
import nl.robinlaugs.kpnmovies.domain.Genre;
import nl.robinlaugs.kpnmovies.domain.Movie;
import org.springframework.batch.item.ItemProcessor;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class MovieItemProcessor implements ItemProcessor<MovieDto, Movie> {

    @Override
    public Movie process(MovieDto dto) {
        return Movie.builder()
                .title(dto.getTitle())
                .rating(dto.getRating())
                .runtime(convertRuntime(dto.getRuntime()))
                .imdb(dto.getImdb())
                .genres(convertGenres(dto.getGenres()))
                .actors(convertActors(dto.getActors()))
                .build();
    }

    Duration convertRuntime(int dto) {
        return Duration.ofMinutes(dto);
    }

    List<Genre> convertGenres(List<String> dtos) {
        return dtos.stream()
                .map(Genre::findByValue)
                .collect(Collectors.toList());
    }

    List<Actor> convertActors(List<ActorDto> dtos) {
        return dtos.stream()
                .map(this::convertActor)
                .collect(Collectors.toList());
    }

    private Actor convertActor(ActorDto dto) {
        return Actor.builder()
                .name(dto.getName())
                .gender(Gender.findByValue(dto.getGender()))
                .build();
    }

}

package nl.robinlaugs.kpnmovies.controller;

import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.controller.dto.MovieDto;
import nl.robinlaugs.kpnmovies.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/kpnmovies/v1/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/suggestion/customer/id/{customerId}")
    ResponseEntity<List<MovieDto>> getSuggestions(@PathVariable String customerId) {
        List<MovieDto> body = movieService.getSuggestions(customerId).stream()
                .map(MovieDto::new)
                .collect(toList());

        return ResponseEntity.ok(body);
    }

}
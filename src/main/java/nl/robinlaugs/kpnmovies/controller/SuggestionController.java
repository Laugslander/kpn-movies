package nl.robinlaugs.kpnmovies.controller;

import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.service.customer.CustomerNotFoundException;
import nl.robinlaugs.kpnmovies.service.suggestion.SuggestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/kpnmovies")
@RequiredArgsConstructor
public class SuggestionController {

    private final SuggestionService service;

    @GetMapping("/v1/movie/suggestion/customer/id/{customerId}")
    ResponseEntity<List<MovieResponse>> getSuggestion(@PathVariable String customerId) {
        try {
            List<MovieResponse> body = service.getSuggestions(customerId).stream()
                    .map(MovieResponse::new)
                    .collect(toList());

            return ResponseEntity.ok(body);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
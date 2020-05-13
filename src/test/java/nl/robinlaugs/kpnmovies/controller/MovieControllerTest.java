package nl.robinlaugs.kpnmovies.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.robinlaugs.kpnmovies.controller.dto.ExceptionDto;
import nl.robinlaugs.kpnmovies.controller.dto.MovieDto;
import nl.robinlaugs.kpnmovies.model.Movie;
import nl.robinlaugs.kpnmovies.service.MovieService;
import nl.robinlaugs.kpnmovies.service.exception.CustomerNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.List.of;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @MockBean
    private MovieService service;

    @Autowired
    private MockMvc mockMvc;

    private static final String MOVIES_URL = "/api/kpnmovies/v1/movie";
    private static final String SUGGESTIONS_ENDPOINT_URL = "/suggestion/customer/id/";

    @Test
    public void getSuggestionRequest_knownCustomerId_returnsStatusOkWithCorrectBody() throws Exception {
        Movie movie = Movie.builder()
                .title("title")
                .build();

        Mockito.when(service.getSuggestions("1")).thenReturn(of(movie));

        List<MovieDto> response = of(new MovieDto(movie));
        String json = new ObjectMapper().writeValueAsString(response);

        mockMvc.perform(get(MOVIES_URL + SUGGESTIONS_ENDPOINT_URL + "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(json));

        Mockito.verify(service).getSuggestions("1");
    }

    @Test
    public void getSuggestionRequest_unknownCustomerId_returnsStatusNotFound() throws Exception {
        Mockito.doThrow(new CustomerNotFoundException("Customer with id 0 was not found"))
                .when(service).getSuggestions("0");

        ExceptionDto response = new ExceptionDto("Customer with id 0 was not found");
        String json = new ObjectMapper().writeValueAsString(response);

        mockMvc.perform(get(MOVIES_URL + SUGGESTIONS_ENDPOINT_URL + "0"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(json));

        Mockito.verify(service).getSuggestions("0");
    }

}
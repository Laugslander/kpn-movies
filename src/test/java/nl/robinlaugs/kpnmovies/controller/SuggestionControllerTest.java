package nl.robinlaugs.kpnmovies.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.robinlaugs.kpnmovies.domain.Movie;
import nl.robinlaugs.kpnmovies.service.customer.CustomerNotFoundException;
import nl.robinlaugs.kpnmovies.service.suggestion.SuggestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SuggestionController.class)
class SuggestionControllerTest {

    @MockBean
    private SuggestionService service;

    @Autowired
    private MockMvc mockMvc;

    private static final String GET_SUGGESTIONS_ENDPOINT_URL = "/api/kpnmovies/v1/movie/suggestion/customer/id/";

    @Test
    public void getSuggestionRequest_knownCustomerId_returnsStatusOkWithCorrectBody() throws Exception {
        Movie movie = Movie.builder()
                .title("title")
                .build();

        when(service.getSuggestions("1")).thenReturn(List.of(movie));

        List<MovieResponse> response = List.of(new MovieResponse(movie));
        String json = new ObjectMapper().writeValueAsString(response);

        mockMvc.perform(get(GET_SUGGESTIONS_ENDPOINT_URL + "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(json));

        verify(service).getSuggestions("1");
    }

    @Test
    public void getSuggestionRequest_unknownCustomerId_returnsStatusNotFound() throws Exception {
        when(service.getSuggestions("0")).thenThrow(CustomerNotFoundException.class);

        mockMvc.perform(get(GET_SUGGESTIONS_ENDPOINT_URL + "0"))
                .andExpect(status().isNotFound());

        verify(service).getSuggestions("0");
    }

}
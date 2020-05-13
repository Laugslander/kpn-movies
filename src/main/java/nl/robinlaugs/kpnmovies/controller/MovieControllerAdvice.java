package nl.robinlaugs.kpnmovies.controller;

import nl.robinlaugs.kpnmovies.controller.dto.ExceptionDto;
import nl.robinlaugs.kpnmovies.service.exception.CustomerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class MovieControllerAdvice {

    @ResponseBody
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    ResponseEntity<ExceptionDto> customerNotFoundHandler(CustomerNotFoundException e) {
        ExceptionDto dto = new ExceptionDto(e.getMessage());

        return ResponseEntity.status(NOT_FOUND).body(dto);
    }

}

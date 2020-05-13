package nl.robinlaugs.kpnmovies.controller.dto;

import lombok.Getter;

@Getter
public class ExceptionDto {

    private final String message;

    public ExceptionDto(String message) {
        this.message = message;
    }

}

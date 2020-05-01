package nl.robinlaugs.kpnmovies.batch.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class ProfileDto {

    private String customer_id;
    private String name;
    private Map<String, String>[] interests;

}

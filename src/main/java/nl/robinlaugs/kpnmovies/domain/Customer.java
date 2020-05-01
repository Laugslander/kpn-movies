package nl.robinlaugs.kpnmovies.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Builder
@Document(collection = "customers")
public class Customer {

    @Id
    private String id;

    private String name;
    private Map<Interest, String> interests;

}

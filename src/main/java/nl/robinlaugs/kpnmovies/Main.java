package nl.robinlaugs.kpnmovies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// Exclude prevents Spring Batch from expecting a JDBC resource since this application uses MongoDB
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}

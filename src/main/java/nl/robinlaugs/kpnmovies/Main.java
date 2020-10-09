package nl.robinlaugs.kpnmovies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// Prevent Spring Batch from expecting a JDBC resource since we're using MongoDB
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}

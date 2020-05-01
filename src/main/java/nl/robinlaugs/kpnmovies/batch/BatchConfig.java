package nl.robinlaugs.kpnmovies.batch;

import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.batch.dto.ActorDto;
import nl.robinlaugs.kpnmovies.batch.dto.MovieDto;
import nl.robinlaugs.kpnmovies.batch.dto.ProfileDto;
import nl.robinlaugs.kpnmovies.batch.processor.CustomerItemProcessor;
import nl.robinlaugs.kpnmovies.batch.processor.MovieItemProcessor;
import nl.robinlaugs.kpnmovies.domain.Customer;
import nl.robinlaugs.kpnmovies.domain.Movie;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.Map;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private static final String JOB_NAME = "import job";

    private static final String STEP_NAME_MOVIES = "import movies";
    private static final String STEP_NAME_CUSTOMERS = "import customers";

    private static final int STEP_CHUNK_SIZE = 3;

    private static final String READER_NAME_MOVIES = "movies reader";
    private static final String READER_NAME_CUSTOMERS = "customers reader";

    private static final String RESOURCE_NAME_MOVIES = "movies.xml";
    private static final String RESOURCE_NAME_PROFILES = "profiles.json";

    private static final String MONGODB_COLLECTION_NAME_MOVIES = "movies";
    private static final String MONGODB_COLLECTION_NAME_CUSTOMERS = "customers";

    private static final String XML_ROOT_ELEMENT_NAME = "movie";
    private static final Map<String, Class<?>> XSTREAM_ALIASES = Map.of(
            "movie", MovieDto.class,
            "actor", ActorDto.class,
            "genre", String.class
    );

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final MongoTemplate mongoTemplate;

    @Bean
    public Job job() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(importMovies())
                .next(importCustomers())
                .build();
    }

    @Bean
    public Step importMovies() {
        return stepBuilderFactory.get(STEP_NAME_MOVIES)
                .<MovieDto, Movie>chunk(STEP_CHUNK_SIZE)
                .reader(moviesReader())
                .processor(new MovieItemProcessor())
                .writer(moviesWriter())
                .build();
    }

    @Bean
    public Step importCustomers() {
        return stepBuilderFactory.get(STEP_NAME_CUSTOMERS)
                .<ProfileDto, Customer>chunk(STEP_CHUNK_SIZE)
                .reader(customersReader())
                .processor(new CustomerItemProcessor())
                .writer(customersWriter())
                .build();
    }

    @Bean
    public ItemReader<MovieDto> moviesReader() {
        XStreamMarshaller unmarshaller = new XStreamMarshaller();
        unmarshaller.setAliases(XSTREAM_ALIASES);

        return new StaxEventItemReaderBuilder<MovieDto>()
                .name(READER_NAME_MOVIES)
                .resource(new ClassPathResource(RESOURCE_NAME_MOVIES))
                .addFragmentRootElements((XML_ROOT_ELEMENT_NAME))
                .unmarshaller(unmarshaller)
                .build();
    }

    @Bean
    public ItemWriter<Movie> moviesWriter() {
        return new MongoItemWriterBuilder<Movie>()
                .template(mongoTemplate)
                .collection(MONGODB_COLLECTION_NAME_MOVIES)
                .build();
    }

    @Bean
    public ItemReader<ProfileDto> customersReader() {
        return new JsonItemReaderBuilder<ProfileDto>()
                .name(READER_NAME_CUSTOMERS)
                .resource(new ClassPathResource(RESOURCE_NAME_PROFILES))
                .jsonObjectReader(new JacksonJsonObjectReader<>(ProfileDto.class))
                .build();
    }

    @Bean
    public ItemWriter<Customer> customersWriter() {
        return new MongoItemWriterBuilder<Customer>()
                .template(mongoTemplate)
                .collection(MONGODB_COLLECTION_NAME_CUSTOMERS)
                .build();
    }

}

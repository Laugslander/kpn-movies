package nl.robinlaugs.kpnmovies.batch.processor;

import nl.robinlaugs.kpnmovies.batch.dto.ProfileDto;
import nl.robinlaugs.kpnmovies.batch.processor.util.RatingsExtractor;
import nl.robinlaugs.kpnmovies.batch.processor.util.RuntimeExtractor;
import nl.robinlaugs.kpnmovies.model.Customer;
import nl.robinlaugs.kpnmovies.model.Gender;
import nl.robinlaugs.kpnmovies.model.Genre;
import nl.robinlaugs.kpnmovies.model.Interest;
import nl.robinlaugs.kpnmovies.model.InterestCategory;
import nl.robinlaugs.kpnmovies.model.InterestOperator;
import org.springframework.batch.item.ItemProcessor;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static nl.robinlaugs.kpnmovies.model.InterestCategory.ACTORS;
import static nl.robinlaugs.kpnmovies.model.InterestCategory.GENDER;
import static nl.robinlaugs.kpnmovies.model.InterestCategory.GENRES;
import static nl.robinlaugs.kpnmovies.model.InterestCategory.RATINGS;
import static nl.robinlaugs.kpnmovies.model.InterestCategory.RUNTIME;

public class CustomerItemProcessor implements ItemProcessor<ProfileDto, Customer> {

    @Override
    public Customer process(ProfileDto dto) {
        return Customer.builder()
                .id(dto.getCustomer_id())
                .name(dto.getName())
                .interests(convertInterests(dto.getInterests()))
                .build();
    }

    private List<Interest<?>> convertInterests(Map<String, String>[] interests) {
        return interests[0].entrySet().stream()
                .map(entry -> convertInterest(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    Interest<?> convertInterest(String interest, String value) {
        InterestCategory category = InterestCategory.findByValue(interest);

        return switch (category) {
            case RATINGS -> determineRatingInterest(value);
            case RUNTIME -> determineRuntimeInterest(value);
            case GENRES -> determineGenreInterest(value);
            case ACTORS -> determineActorNamesInterest(value);
            case GENDER -> determineActorGenderInterest(value);
        };
    }

    Interest<Double> determineRatingInterest(String value) {
        String operatorValue = value.substring(value.length() - 1);

        InterestOperator operator = InterestOperator.findByValue(operatorValue);
        double rating = RatingsExtractor.extract(value, operator);

        return Interest.<Double>builder()
                .category(RATINGS)
                .operator(operator)
                .value(rating)
                .build();
    }

    Interest<Duration> determineRuntimeInterest(String value) {
        String[] valueAttributes = value.split(" ");
        String operatorValue = valueAttributes[0];

        InterestOperator operator = InterestOperator.findByValue(operatorValue);
        Duration runtime = RuntimeExtractor.extract(valueAttributes, operator);

        return Interest.<Duration>builder()
                .category(RUNTIME)
                .operator(operator)
                .value(runtime)
                .build();
    }

    Interest<Genre> determineGenreInterest(String value) {
        Genre genre = Genre.findByValue(value);

        return Interest.<Genre>builder()
                .category(GENRES)
                .value(genre)
                .build();
    }

    Interest<String> determineActorNamesInterest(String value) {
        return Interest.<String>builder()
                .category(ACTORS)
                .value(value)
                .build();
    }

    Interest<Gender> determineActorGenderInterest(String value) {
        Gender gender = Gender.findByValue(value);

        return Interest.<Gender>builder()
                .category(GENDER)
                .value(gender)
                .build();
    }

}

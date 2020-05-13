package nl.robinlaugs.kpnmovies.repository;

import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.model.Interest;
import nl.robinlaugs.kpnmovies.model.Movie;
import nl.robinlaugs.kpnmovies.repository.util.MovieKey;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
@RequiredArgsConstructor
public class MovieCustomRepositoryImpl implements MovieCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Movie> findByInterests(List<Interest<?>> interests) {
        Query query = createQuery(interests);

        return mongoTemplate.find(query, Movie.class);
    }

    private Query createQuery(List<Interest<?>> interests) {
        Criteria[] orCriteria = interests.stream()
                .map(this::createCriteria)
                .toArray(Criteria[]::new);

        Criteria criteria = new Criteria().orOperator(orCriteria);

        return new Query(criteria);
    }

    private Criteria createCriteria(Interest<?> interest) {
        String key = MovieKey.from(interest.getCategory()).getKey();
        Object value = interest.getValue();

        return switch (interest.getOperator()) {
            case NONE -> where(key).is(value);
            case GREATER_THEN -> where(key).gt(value);
            case GREATER_THEN_OR_EQUAL_TO -> where(key).gte(value);
            case LESS_THEN -> where(key).lt(value);
            case LESS_THEN_OR_EQUAL_TO -> where(key).lte(value);
        };
    }

}

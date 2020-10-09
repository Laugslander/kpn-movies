package nl.robinlaugs.kpnmovies.repository;

import lombok.RequiredArgsConstructor;
import nl.robinlaugs.kpnmovies.model.Interest;
import nl.robinlaugs.kpnmovies.model.Movie;
import nl.robinlaugs.kpnmovies.repository.util.MongoMovieKey;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        String key = MongoMovieKey.from(interest.getCategory()).getKey();
        Object value = interest.getValue();

        return switch (interest.getOperator()) {
            case NONE -> Criteria.where(key).is(value);
            case GREATER_THEN -> Criteria.where(key).gt(value);
            case GREATER_THEN_OR_EQUAL_TO -> Criteria.where(key).gte(value);
            case LESS_THEN -> Criteria.where(key).lt(value);
            case LESS_THEN_OR_EQUAL_TO -> Criteria.where(key).lte(value);
        };
    }

}

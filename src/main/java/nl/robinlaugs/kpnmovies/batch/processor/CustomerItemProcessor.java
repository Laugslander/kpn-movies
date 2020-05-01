package nl.robinlaugs.kpnmovies.batch.processor;

import nl.robinlaugs.kpnmovies.batch.dto.ProfileDto;
import nl.robinlaugs.kpnmovies.domain.Customer;
import nl.robinlaugs.kpnmovies.domain.Interest;
import org.springframework.batch.item.ItemProcessor;

import java.util.Map;
import java.util.stream.Collectors;

public class CustomerItemProcessor implements ItemProcessor<ProfileDto, Customer> {

    @Override
    public Customer process(ProfileDto dto) {
        return Customer.builder()
                .id(dto.getCustomer_id())
                .name(dto.getName())
                .interests(convertInterests(dto.getInterests()))
                .build();
    }

    private Map<Interest, String> convertInterests(Map<String, String>[] interests) {
        return interests[0].entrySet().stream()
                .collect(Collectors.toMap(e -> Interest.findByValue(e.getKey()), Map.Entry::getValue));
    }

}

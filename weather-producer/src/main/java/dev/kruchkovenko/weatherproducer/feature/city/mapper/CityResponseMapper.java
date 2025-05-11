package dev.kruchkovenko.weatherproducer.feature.city.mapper;

import dev.kruchkovenko.weatherproducer.feature.city.client.model.CityResponse;
import dev.kruchkovenko.weatherproducer.feature.city.model.City;
import org.springframework.stereotype.Component;

@Component
public class CityResponseMapper {
    public City transform(CityResponse cityResponse) {
        var location = cityResponse.getResults().get(0);
        return new City(null, location.getName(), location.getCountryCode(), location.getCoordinate());
    }
}

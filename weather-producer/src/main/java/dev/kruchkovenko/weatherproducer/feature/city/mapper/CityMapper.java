package dev.kruchkovenko.weatherproducer.feature.city.mapper;

import dev.kruchkovenko.weatherproducer.feature.city.model.City;
import dev.kruchkovenko.weatherproducer.feature.city.repository.storage.model.CityEntity;
import org.springframework.stereotype.Component;


@Component
public class CityMapper {
    public CityEntity transform(City city) {
        if (city == null) {
            return null;
        }
        return new CityEntity(city.getName(), city.getCountryCode(), city.getCoordinate());
    }
}

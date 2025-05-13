package dev.kruchkovenko.weatherproducer.feature.city.mapper;

import dev.kruchkovenko.weatherproducer.feature.city.model.City;
import dev.kruchkovenko.weatherproducer.feature.city.repository.storage.model.CityEntity;
import org.springframework.stereotype.Component;


@Component
public class CityEntityMapper {
    public City transform(CityEntity entity) {
        if (entity == null) {
            return null;
        }
        return new City(null, entity.getName(), entity.getCountryCode(), entity.getCoordinate());
    }
}

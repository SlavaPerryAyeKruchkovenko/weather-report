package dev.kruchkovenko.app.feature.repository;

import dev.kruchkovenko.app.feature.model.AvgWeather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RedisWeatherRepository extends CrudRepository<AvgWeather, String> {
    List<AvgWeather> findByCityAndCountryCode(String city, String countryCode);
}

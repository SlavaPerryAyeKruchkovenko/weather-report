package dev.kruchkovenko.weatherconsumer.feature.weather.repository;

import dev.kruchkovenko.weatherconsumer.feature.weather.model.AvgWeather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisWeatherRepository extends CrudRepository<AvgWeather, String> {
}

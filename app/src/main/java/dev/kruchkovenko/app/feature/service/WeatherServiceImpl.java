package dev.kruchkovenko.app.feature.service;


import dev.kruchkovenko.app.feature.model.AvgWeather;
import dev.kruchkovenko.app.feature.repository.RedisWeatherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class WeatherServiceImpl implements WeatherService {
    private final RedisWeatherRepository repository;

    public WeatherServiceImpl(RedisWeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AvgWeather> getAllWeatherByCityAndCountryCodeAndTime(
            String city,
            String countryCode,
            LocalDateTime measureTime
    ) {
        var targetDate = measureTime.toLocalDate();
        return repository.findByCityAndCountryCode(city, countryCode).stream()
                .filter(weather -> weather.getMeasureTime().toLocalDate().equals(targetDate))
                .toList();
    }
}

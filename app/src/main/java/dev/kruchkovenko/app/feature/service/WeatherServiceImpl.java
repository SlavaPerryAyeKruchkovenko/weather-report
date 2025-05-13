package dev.kruchkovenko.app.feature.service;


import dev.kruchkovenko.app.feature.mapper.AvgWeatherMapper;
import dev.kruchkovenko.app.feature.model.AvgWeather;
import dev.kruchkovenko.app.feature.model.Weather;
import dev.kruchkovenko.app.feature.repository.RedisWeatherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Service
public class WeatherServiceImpl implements WeatherService {
    private final RedisWeatherRepository repository;
    private final AvgWeatherMapper mapper;

    public WeatherServiceImpl(RedisWeatherRepository repository, AvgWeatherMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Weather> getAllWeather(
            String city,
            String countryCode,
            LocalDate measureTime
    ) {
        return repository.findByCityAndCountryCode(city, countryCode).stream()
                .filter(weather -> weather.getMeasureTime().toLocalDate().equals(measureTime))
                .map(mapper::transform)
                .toList();
    }

    @Override
    public List<Weather> getAllWeather(
            String city,
            String countryCode
    ) {
        var weather = repository.findByCityAndCountryCode(city, countryCode).stream()
                .max(Comparator.comparing(AvgWeather::getMeasureTime))
                .map(mapper::transform)
                .orElse(null);
        if (weather == null) {
            return Collections.emptyList();
        }
        return List.of(weather);
    }
}

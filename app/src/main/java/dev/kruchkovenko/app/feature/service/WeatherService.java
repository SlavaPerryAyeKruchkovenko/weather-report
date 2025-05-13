package dev.kruchkovenko.app.feature.service;

import dev.kruchkovenko.app.feature.model.Weather;

import java.time.LocalDate;
import java.util.List;


public interface WeatherService {
    List<Weather> getAllWeather(
            String city,
            String countryCode,
            LocalDate measureTime
    );

    List<Weather> getAllWeather(
            String city,
            String countryCode
    );
}

package dev.kruchkovenko.app.feature.service;

import dev.kruchkovenko.app.feature.model.AvgWeather;

import java.time.LocalDateTime;
import java.util.List;


public interface WeatherService {
    List<AvgWeather> getAllWeatherByCityAndCountryCodeAndTime(
            String city,
            String countryCode,
            LocalDateTime measureTime
    );
}

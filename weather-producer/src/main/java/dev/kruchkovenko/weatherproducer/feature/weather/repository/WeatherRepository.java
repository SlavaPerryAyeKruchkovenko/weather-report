package dev.kruchkovenko.weatherproducer.feature.weather.repository;

import dev.kruchkovenko.weatherproducer.feature.weather.model.Weather;

public interface WeatherRepository {
    void saveWeather(Weather weather);
}

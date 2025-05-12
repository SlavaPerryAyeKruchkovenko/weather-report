package dev.kruchkovenko.weatherproducer.feature.weather.repository;

import dev.kruchkovenko.weatherproducer.feature.weather.model.Weather;

import java.util.List;

public interface WeatherRepository {
    void saveAllWeather(List<Weather> weathers);
}

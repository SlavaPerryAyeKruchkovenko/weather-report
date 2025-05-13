package dev.kruchkovenko.weatherconsumer.feature.weather.service;

import dev.kruchkovenko.weatherconsumer.feature.weather.model.AvgWeather;

public interface WeatherService {
    void save(AvgWeather avgWeather);
}

package dev.kruchkovenko.weatherconsumer.feature.weather.service;

import dev.kruchkovenko.weatherconsumer.feature.weather.model.AvgWeather;
import dev.kruchkovenko.weatherconsumer.feature.weather.repository.RedisWeatherRepository;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final RedisWeatherRepository repository;

    public WeatherServiceImpl(RedisWeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(AvgWeather avgWeather) {
        repository.save(avgWeather);
    }
}

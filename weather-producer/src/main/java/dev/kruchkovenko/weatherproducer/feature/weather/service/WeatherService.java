package dev.kruchkovenko.weatherproducer.feature.weather.service;

import dev.kruchkovenko.weatherproducer.feature.weather.model.Weather;
import dev.kruchkovenko.weatherproducer.shared.Coordinate;
import reactor.core.publisher.Flux;

import java.util.List;

public interface WeatherService {
    Flux<Weather> fetchWeathersByCoordinate(Coordinate coordinate);
    void saveAllWeathers(List<Weather> weathers);
}

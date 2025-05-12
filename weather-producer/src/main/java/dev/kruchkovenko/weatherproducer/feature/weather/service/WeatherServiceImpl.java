package dev.kruchkovenko.weatherproducer.feature.weather.service;

import dev.kruchkovenko.weatherproducer.feature.weather.client.meteo.MeteoWebClient;
import dev.kruchkovenko.weatherproducer.feature.weather.client.yandex.YandexWebClient;
import dev.kruchkovenko.weatherproducer.feature.weather.model.Weather;
import dev.kruchkovenko.weatherproducer.shared.Coordinate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final YandexWebClient yandexWebClient;
    private final MeteoWebClient meteoWebClient;

    public WeatherServiceImpl(YandexWebClient yandexWebClient, MeteoWebClient meteoWebClient) {
        this.yandexWebClient = yandexWebClient;
        this.meteoWebClient = meteoWebClient;
    }

    @Override
    public Flux<Weather> fetchWeathersByCoordinate(Coordinate coordinate) {
        var yandexWeather = yandexWebClient.getWeather(coordinate);
        var meteoWeather = meteoWebClient.getWeather(coordinate);

        return Flux.merge(yandexWeather, meteoWeather);
    }
}

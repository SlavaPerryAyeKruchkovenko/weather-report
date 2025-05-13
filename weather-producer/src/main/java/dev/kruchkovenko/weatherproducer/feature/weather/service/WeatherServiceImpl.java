package dev.kruchkovenko.weatherproducer.feature.weather.service;

import dev.kruchkovenko.weatherproducer.feature.weather.client.meteo.MeteoWebClient;
import dev.kruchkovenko.weatherproducer.feature.weather.client.yandex.YandexWebClient;
import dev.kruchkovenko.weatherproducer.feature.weather.model.Weather;
import dev.kruchkovenko.weatherproducer.feature.weather.repository.WeatherRepository;
import dev.kruchkovenko.weatherproducer.shared.Coordinate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final YandexWebClient yandexWebClient;
    private final MeteoWebClient meteoWebClient;
    private final WeatherRepository repository;

    public WeatherServiceImpl(YandexWebClient yandexWebClient, MeteoWebClient meteoWebClient, WeatherRepository repository) {
        this.yandexWebClient = yandexWebClient;
        this.meteoWebClient = meteoWebClient;
        this.repository = repository;
    }

    @Override
    public Flux<Weather> fetchWeathersByCoordinate(Coordinate coordinate) {
        var yandexWeather = yandexWebClient.getWeather(coordinate);
        var meteoWeather = meteoWebClient.getWeather(coordinate);

        return Flux.merge(yandexWeather, meteoWeather);
    }

    @Override
    public void saveAllWeathers(List<Weather> weathers) {
        repository.saveAllWeather(weathers);
    }
}

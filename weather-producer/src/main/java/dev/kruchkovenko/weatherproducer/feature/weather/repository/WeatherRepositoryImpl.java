package dev.kruchkovenko.weatherproducer.feature.weather.repository;

import dev.kruchkovenko.weatherproducer.feature.weather.model.Weather;
import dev.kruchkovenko.weatherproducer.feature.weather.repository.producer.WeatherProducer;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {
    private final WeatherProducer producer;

    WeatherRepositoryImpl(WeatherProducer producer) {
        this.producer = producer;
    }

    @Override
    public void saveWeather(Weather weather) {
        this.producer.sendWeathers(weather);
    }
}

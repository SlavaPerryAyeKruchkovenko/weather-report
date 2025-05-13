package dev.kruchkovenko.weatherproducer.feature.weather.repository;

import dev.kruchkovenko.weatherproducer.feature.weather.model.Weather;
import dev.kruchkovenko.weatherproducer.feature.weather.repository.producer.WeatherProducer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository {
    private final WeatherProducer producer;

    WeatherRepositoryImpl(WeatherProducer producer) {
        this.producer = producer;
    }

    @Override
    public void saveAllWeather(List<Weather> weathers) {
        this.producer.sendWeathers(weathers);
    }
}

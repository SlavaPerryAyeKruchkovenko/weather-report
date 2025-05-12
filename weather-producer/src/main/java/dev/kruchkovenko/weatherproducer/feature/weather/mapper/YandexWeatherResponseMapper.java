package dev.kruchkovenko.weatherproducer.feature.weather.mapper;

import dev.kruchkovenko.weatherproducer.feature.weather.client.yandex.model.YandexWeatherResponse;
import dev.kruchkovenko.weatherproducer.feature.weather.model.Weather;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class YandexWeatherResponseMapper {
    public Weather transform(YandexWeatherResponse response, LocalDateTime requestDate) {
        if (response == null) return null;
        var fact = response.getFact();
        if (fact == null) return null;
        return new Weather(fact.getTemperature(), fact.getTime(), requestDate);
    }
}

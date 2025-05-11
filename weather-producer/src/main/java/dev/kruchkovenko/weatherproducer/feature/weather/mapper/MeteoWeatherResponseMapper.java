package dev.kruchkovenko.weatherproducer.feature.weather.mapper;

import dev.kruchkovenko.weatherproducer.feature.weather.client.meteo.model.MeteoWeatherResponse;
import dev.kruchkovenko.weatherproducer.feature.weather.model.Weather;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MeteoWeatherResponseMapper {
    public Weather transform(MeteoWeatherResponse response, LocalDateTime requestDate) {
        if (response == null) return null;
        var currentWeather = response.getCurrentWeather();
        if (currentWeather == null) return null;
        return new Weather(currentWeather.getTemperature(), currentWeather.getTime(), requestDate);
    }
}

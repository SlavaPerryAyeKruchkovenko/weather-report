package dev.kruchkovenko.weatherconsumer.feature.weather.util;

import dev.kruchkovenko.weatherconsumer.feature.weather.model.AvgWeather;
import dev.kruchkovenko.weatherconsumer.feature.weather.model.Weather;

import java.util.UUID;

public class WeatherUtil {
    public static AvgWeather getAvgWeather(Weather weather) throws IllegalArgumentException {
        if (weather != null && !weather.getForecasts().isEmpty()) {
            var forecasts = weather.getForecasts();
            var averageTemp = forecasts.stream()
                    .mapToDouble(Weather.Forecast::getTemperature)
                    .average()
                    .orElse(0.0);
            var id = UUID.randomUUID().toString();
            return new AvgWeather(
                    id,
                    weather.getCity(),
                    weather.getCountryCode(),
                    weather.getMeasureTime(),
                    averageTemp
            );
        }
        throw new IllegalArgumentException("Weather is null or empty");
    }
}

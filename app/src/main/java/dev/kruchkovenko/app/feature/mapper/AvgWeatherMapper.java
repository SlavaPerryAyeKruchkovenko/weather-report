package dev.kruchkovenko.app.feature.mapper;

import dev.kruchkovenko.app.feature.model.AvgWeather;
import dev.kruchkovenko.app.feature.model.Weather;
import org.springframework.stereotype.Component;

@Component
public class AvgWeatherMapper {
    public Weather transform(AvgWeather avgWeather) {
        return new Weather(
                avgWeather.getCity(),
                avgWeather.getCountryCode(),
                avgWeather.getMeasureTime(),
                avgWeather.getTemperature()
        );
    }
}

package dev.kruchkovenko.weatherconsumer.weather;

import dev.kruchkovenko.weatherconsumer.feature.weather.model.Weather;
import dev.kruchkovenko.weatherconsumer.feature.weather.util.WeatherUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WeatherUtilTest {

    @ParameterizedTest
    @MethodSource("weatherDataProvider")
    void getAvgWeatherWithMultiplyForecast(List<Weather.Forecast> forecasts, Double expectedAvg) {
        var city = RandomStringUtils.random(10);
        var countryCode = RandomStringUtils.random(2);
        Weather weather = new Weather(
                city, countryCode,
                LocalDateTime.now(),
                forecasts
        );

        var result = WeatherUtil.getAvgWeather(weather);

        assertEquals(city, result.getCity());
        assertEquals(countryCode, result.getCountryCode());
        assertEquals(expectedAvg, result.getTemperature());
        assertNotNull(result.getId());
        assertEquals(weather.getMeasureTime(), result.getMeasureTime());
    }

    @Test
    void getAvgWeatherWithMultiplyForecastGenerated() {
        var size = new Random().nextInt(5, 51);
        var forecasts = DoubleStream.generate(() -> new Random().nextDouble())
                .limit(size)
                .mapToObj(Weather.Forecast::new).toList();
        var city = RandomStringUtils.random(10);
        var countryCode = RandomStringUtils.random(2);
        Weather weather = new Weather(
                city, countryCode,
                LocalDateTime.now(),
                forecasts
        );

        var result = WeatherUtil.getAvgWeather(weather);
        var expectedAvgTemperature = forecasts.stream()
                .mapToDouble(Weather.Forecast::getTemperature)
                .average()
                .orElseThrow();

        assertEquals(city, result.getCity());
        assertEquals(countryCode, result.getCountryCode());
        assertEquals(expectedAvgTemperature, result.getTemperature());
        assertNotNull(result.getId());
        assertEquals(weather.getMeasureTime(), result.getMeasureTime());
    }

    @Test
    void getAvgWeatherWithNullInput() {
        assertThrows(
                IllegalArgumentException.class,
                () -> WeatherUtil.getAvgWeather(null)
        );
    }

    private static Stream<Arguments> weatherDataProvider() {
        return Stream.of(
                Arguments.of(List.of(
                        new Weather.Forecast(20.0),
                        new Weather.Forecast(30.0)
                ), 25.0),
                Arguments.of(List.of(
                        new Weather.Forecast(20.0)
                ), 20.0),
                Arguments.of(List.of(
                        new Weather.Forecast(-160.0),
                        new Weather.Forecast(100.0),
                        new Weather.Forecast(120.0),
                        new Weather.Forecast(12.0),
                        new Weather.Forecast(-28.0)
                ), 8.8)
        );
    }
}

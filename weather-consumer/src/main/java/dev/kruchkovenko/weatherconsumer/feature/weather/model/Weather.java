package dev.kruchkovenko.weatherconsumer.feature.weather.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Weather {
    private final String city;
    private final String countryCode;
    private final List<Forecast> forecasts;
    private final LocalDateTime measureTime;

    public Weather(String city, String countryCode, LocalDateTime measureTime, List<Forecast> forecasts) {
        this.city = city;
        this.countryCode = countryCode;
        this.forecasts = forecasts;
        this.measureTime = measureTime;
    }

    public static class Forecast {
        private final Double temperature;


        public Forecast(Double temperature) {
            this.temperature = temperature;
        }

        public Double getTemperature() {
            return this.temperature;
        }

        @Override
        public String toString() {
            return String.format(
                    "{ temperature: %s }",
                    temperature
            );
        }
    }


    public String getCity() {
        return this.city;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public LocalDateTime getMeasureTime() {
        return this.measureTime;
    }

    public List<Forecast> getForecasts() {
        return this.forecasts;
    }

    @Override
    public String toString() {
        return String.format(
                "{ forecast : [%s] , city : %s , countryCode : %s }",
                forecasts.stream().map(Object::toString).collect(Collectors.joining(", ")),
                city,
                countryCode
        );
    }
}

package dev.kruchkovenko.weatherconsumer.feature.weather.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Weather {
    private final String city;
    private final String countryCode;
    private final List<Forecast> forecasts;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private final LocalDateTime measureTime;

    @JsonCreator
    public Weather(
            @JsonProperty("city") String city,
            @JsonProperty("countryCode") String countryCode,
            @JsonProperty("measureTime") LocalDateTime measureTime,
            @JsonProperty("forecasts") List<Forecast> forecasts
    ) {
        this.city = city;
        this.countryCode = countryCode;
        this.forecasts = forecasts;
        this.measureTime = measureTime;
    }

    public static class Forecast {
        private final Double temperature;

        @JsonCreator
        public Forecast(@JsonProperty("temperature") Double temperature) {
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

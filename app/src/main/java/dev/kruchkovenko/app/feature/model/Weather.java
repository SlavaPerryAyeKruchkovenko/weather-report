package dev.kruchkovenko.app.feature.model;

import java.time.LocalDateTime;

public class Weather {

    private final String city;
    private final String countryCode;
    private final Double temperature;
    private final LocalDateTime measureTime;

    public Weather(String city, String countryCode, LocalDateTime measureTime, Double temperature) {
        this.city = city;
        this.countryCode = countryCode;
        this.temperature = temperature;
        this.measureTime = measureTime;
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

    public Double getTemperature() {
        return this.temperature;
    }

    @Override
    public String toString() {
        return String.format(
                "{ temperature : %s , city : %s , countryCode : %s , measureTime : %s }",
                temperature,
                city,
                countryCode,
                measureTime
        );
    }
}

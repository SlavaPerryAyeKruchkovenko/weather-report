package dev.kruchkovenko.weatherproducer.feature.weather.model;

import java.time.LocalDateTime;

public class Weather {
    private final Double temperature;
    private final LocalDateTime measureTime;
    private final LocalDateTime requestTime;

    public Weather(Double temperature, LocalDateTime time, LocalDateTime requestDate) {
        this.temperature = temperature;
        this.measureTime = time;
        this.requestTime = requestDate;
    }

    public Double getTemperature() {
        return this.temperature;
    }

    public LocalDateTime getMeasureTime() {
        return this.measureTime;
    }

    public LocalDateTime getRequestTime() {
        return this.requestTime;
    }
}

package dev.kruchkovenko.weatherconsumer.feature.weather.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;


@RedisHash("AvgWeather")
public class AvgWeather implements Serializable {
    @Id
    private String id;
    @Indexed
    private String city;
    @Indexed
    private String countryCode;
    private Double temperature;
    private LocalDateTime measureTime;

    public AvgWeather() {
    }

    public AvgWeather(String id, String city, String countryCode, LocalDateTime measureTime, Double temperature) {
        this.id = id;
        this.city = city;
        this.countryCode = countryCode;
        this.temperature = temperature;
        this.measureTime = measureTime;
    }

    public String getId() {
        return this.id;
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
                "{ id : %s , temperature : %s , city : %s , countryCode : %s , measureTime : %s }",
                id,
                temperature,
                city,
                countryCode,
                measureTime
        );
    }

    @TimeToLive
    public long getTimeToLive() {
        return 3 * 24 * 60 * 60;
    }
}

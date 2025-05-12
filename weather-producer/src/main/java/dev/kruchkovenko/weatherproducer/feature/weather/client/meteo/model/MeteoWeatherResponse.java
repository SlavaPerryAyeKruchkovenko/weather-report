package dev.kruchkovenko.weatherproducer.feature.weather.client.meteo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class MeteoWeatherResponse {
    @JsonProperty("generationtime_ms")
    private Double generationtimeMs;

    @JsonProperty("current_weather")
    private CurrentWeather currentWeather;

    public CurrentWeather getCurrentWeather() {
        return this.currentWeather;
    }

    public static class CurrentWeather {
        private Double temperature;
        private Double windspeed;
        private Integer winddirection;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime time;

        public Double getTemperature() {
            return this.temperature;
        }

        public Double getWindspeed() {
            return this.windspeed;
        }

        public Integer getWinddirection() {
            return this.winddirection;
        }

        public LocalDateTime getTime() {
            return this.time;
        }
    }
}


package dev.kruchkovenko.weatherproducer.feature.weather.client.yandex.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class YandexWeatherResponse {
    private YandexFact fact;

    public static class YandexFact {

        @JsonCreator
        public YandexFact(@JsonProperty("obs_time") long obsTime) {
            this.time = LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(obsTime),
                    ZoneId.systemDefault()
            );
        }

        @JsonProperty("temp")
        private Double temperature;
        @JsonProperty("wind_speed")
        private Double windSpeed;
        @JsonProperty("wind_dir")
        private String winddirection;
        private final LocalDateTime time;

        public Double getTemperature() {
            return this.temperature;
        }

        public Double getWindspeed() {
            return this.windSpeed;
        }

        public String getWinddirection() {
            return this.winddirection;
        }

        public LocalDateTime getTime() {
            return this.time;
        }
    }

    public YandexFact getFact() {
        return this.fact;
    }
}


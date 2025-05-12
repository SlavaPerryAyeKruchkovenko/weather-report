package dev.kruchkovenko.weatherproducer.feature.weather.client.yandex.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class YandexWeatherResponse {
    private YandexFact fact;

    public static class YandexFact {
        @JsonProperty("temp")
        private Double temperature;
        @JsonProperty("wind_speed")
        private Double windSpeed;
        @JsonProperty("wind_dir")
        private String winddirection;
        @JsonProperty("obs_time")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        private LocalDateTime time;

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


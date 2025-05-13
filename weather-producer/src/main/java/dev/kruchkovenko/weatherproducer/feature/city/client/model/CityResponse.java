package dev.kruchkovenko.weatherproducer.feature.city.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.kruchkovenko.weatherproducer.shared.Coordinate;

import java.util.List;

public class CityResponse {
    private List<Location> results;
    @JsonProperty("generationtime_ms")
    private Double generationtimeMs;

    public static class Location {
        private String name;
        private Double latitude;
        private Double longitude;
        @JsonProperty("country_code")
        private String countryCode;

        public String getCountryCode() {
            return this.countryCode;
        }

        public String getName() {
            return this.name;
        }

        public Double getLatitude() {
            return this.latitude;
        }

        public Double getLongitude() {
            return this.longitude;
        }

        public Coordinate getCoordinate() {
            return new Coordinate(this.latitude, this.longitude);
        }
    }

    public List<Location> getResults() {
        return this.results;
    }

}
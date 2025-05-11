package dev.kruchkovenko.weatherproducer.feature.city.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.kruchkovenko.weatherproducer.feature.city.model.Coordinate;

import java.util.List;

public class CityResponse {
    private List<Location> results;
    @JsonProperty("generationtime_ms")
    private String generationtimeMs;

    public static class Location {
        private int id;
        private String name;
        private String country;
        @JsonProperty("country_сode")
        private String countryCode;
        private float latitude;
        private float longitude;

        public String getCountryCode(){
            return this.countryCode;
        }
        public String getName(){
            return this.name;
        }
        public Coordinate getCoordinate(){
            return new Coordinate(latitude,longitude);
        }
    }

    public List<Location> getResults() {
        return this.results;
    }
}
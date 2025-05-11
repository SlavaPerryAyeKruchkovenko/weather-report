package dev.kruchkovenko.weatherproducer.feature.city.model;

import dev.kruchkovenko.weatherproducer.shared.Coordinate;
import jakarta.validation.constraints.NotNull;

public class City {

    public City(String id, String name, String countryCode, Coordinate coordinate) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.coordinate = coordinate;
    }

    private final String id;
    private String name;
    private String countryCode;
    private Coordinate coordinate;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(@NotNull Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}

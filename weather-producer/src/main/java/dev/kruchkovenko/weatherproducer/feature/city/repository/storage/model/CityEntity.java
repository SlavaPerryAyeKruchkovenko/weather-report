package dev.kruchkovenko.weatherproducer.feature.city.repository.storage.model;

import dev.kruchkovenko.weatherproducer.feature.city.model.Coordinate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "City")
public class CityEntity {
    public CityEntity() {

    }

    public CityEntity(String name, String countryCode, Coordinate coordinate) {
        this.name = name;
        this.countryCode = countryCode;
        this.latitude = coordinate.latitude();
        this.longitude = coordinate.longitude();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Size(min = 2, max = 2, message = "Country code must be exactly 2 characters")
    @Pattern(regexp = "[A-Za-z]{2}", message = "Country code must be 2 letters")
    private String countryCode;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(this.latitude, this.longitude);
    }
}

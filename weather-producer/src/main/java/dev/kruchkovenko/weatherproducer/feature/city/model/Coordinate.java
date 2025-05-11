package dev.kruchkovenko.weatherproducer.feature.city.model;

public class Coordinate {
    public Coordinate(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private final float latitude;
    private final float longitude;

    public float getLatitude() {
        return this.latitude;
    }

    public float getLongitude() {
        return this.longitude;
    }
}

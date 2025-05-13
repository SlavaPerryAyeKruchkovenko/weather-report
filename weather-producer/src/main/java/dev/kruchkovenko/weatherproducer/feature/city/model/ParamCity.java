package dev.kruchkovenko.weatherproducer.feature.city.model;

public class ParamCity {
    public ParamCity(String name, String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    private final String name;
    private final String countryCode;

    public String getName() {
        return this.name;
    }

    public String getCountryCode() {
        return this.countryCode;
    }
}

package dev.kruchkovenko.weatherproducer.feature.city.model;

public class ParamCity {
    public ParamCity(String cityName, String countryCode) {
        this.cityName = cityName;
        this.countryCode = countryCode;
    }

    private final String cityName;
    private final String countryCode;

    public String getCityName() {
        return this.cityName;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    @Override
    public String toString() {
        return String.format("ParamCity{cityName='%s', countryCode='%s'}", cityName, countryCode);
    }
}

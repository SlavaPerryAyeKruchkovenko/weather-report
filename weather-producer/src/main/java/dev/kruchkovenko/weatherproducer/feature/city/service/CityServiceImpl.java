package dev.kruchkovenko.weatherproducer.feature.city.service;

import dev.kruchkovenko.weatherproducer.feature.city.client.CityWebClient;
import dev.kruchkovenko.weatherproducer.feature.city.repository.CityRepository;

public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityWebClient cityWebClient;

    public CityServiceImpl(CityRepository cityRepository, CityWebClient cityWebClient) {
        this.cityRepository = cityRepository;
        this.cityWebClient = cityWebClient;
    }

    @Override
    public void syncCities() {

    }
}

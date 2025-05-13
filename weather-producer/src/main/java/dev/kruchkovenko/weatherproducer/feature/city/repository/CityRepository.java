package dev.kruchkovenko.weatherproducer.feature.city.repository;

import dev.kruchkovenko.weatherproducer.feature.city.model.City;
import reactor.core.publisher.Mono;

public interface CityRepository {
    Mono<City> getCity(String name, String countryCode);
    Mono<City> save(City city);
}

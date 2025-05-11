package dev.kruchkovenko.weatherproducer.feature.city.service;

import dev.kruchkovenko.weatherproducer.feature.city.model.ParamCity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CityService {
    Mono<Void> syncCities(List<ParamCity> cities);
}

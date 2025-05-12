package dev.kruchkovenko.weatherproducer.feature.city.service;

import dev.kruchkovenko.weatherproducer.feature.city.client.CityWebClient;
import dev.kruchkovenko.weatherproducer.feature.city.model.City;
import dev.kruchkovenko.weatherproducer.feature.city.model.ParamCity;
import dev.kruchkovenko.weatherproducer.feature.city.repository.CityRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityWebClient cityWebClient;

    public CityServiceImpl(CityRepository cityRepository, CityWebClient cityWebClient) {
        this.cityRepository = cityRepository;
        this.cityWebClient = cityWebClient;
    }

    @Override
    public Mono<Void> syncCities(List<ParamCity> cities) {
        return Flux.fromIterable(cities)
                .parallel()
                .flatMap(this::getCity
                )
                .sequential()
                .then();
    }

    @Override
    public Mono<City> getCity(ParamCity city) {
        return cityRepository.getCity(city.getName(), city.getCountryCode()).switchIfEmpty(
                cityWebClient.searchCity(city.getName(), city.getCountryCode())
                        .flatMap(cityRepository::save)
        );
    }
}

package dev.kruchkovenko.weatherproducer.feature.city.repository;

import dev.kruchkovenko.weatherproducer.feature.city.mapper.CityEntityMapper;
import dev.kruchkovenko.weatherproducer.feature.city.mapper.CityMapper;
import dev.kruchkovenko.weatherproducer.feature.city.model.City;
import dev.kruchkovenko.weatherproducer.feature.city.repository.storage.StorageCityRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class CityRepositoryImpl implements CityRepository {
    private final StorageCityRepository storageCityRepository;
    private final CityEntityMapper entityMapper;
    private final CityMapper mapper;

    public CityRepositoryImpl(StorageCityRepository storageCityRepository, CityEntityMapper entityMapper, CityMapper mapper) {
        this.storageCityRepository = storageCityRepository;
        this.entityMapper = entityMapper;
        this.mapper = mapper;
    }

    @Override
    public Mono<City> getCity(String name, String countryCode) {
        return Mono.justOrEmpty(entityMapper.transform(storageCityRepository.findByNameAndCountryCodeIgnoreCase(name, countryCode)));
    }

    public Mono<City> save(City city) {
        var result = this.storageCityRepository.save(mapper.transform(city));
        return Mono.justOrEmpty(entityMapper.transform(result));
    }
}

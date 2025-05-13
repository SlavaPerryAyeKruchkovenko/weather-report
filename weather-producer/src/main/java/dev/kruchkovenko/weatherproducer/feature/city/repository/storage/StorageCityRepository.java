package dev.kruchkovenko.weatherproducer.feature.city.repository.storage;

import dev.kruchkovenko.weatherproducer.feature.city.repository.storage.model.CityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StorageCityRepository extends CrudRepository<CityEntity, Long> {
    CityEntity findByNameAndCountryCodeIgnoreCase(String name, String countryCode);
}
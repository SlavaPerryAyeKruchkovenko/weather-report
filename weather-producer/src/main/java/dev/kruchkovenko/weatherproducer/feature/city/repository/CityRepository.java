package dev.kruchkovenko.weatherproducer.feature.city.repository;

import dev.kruchkovenko.weatherproducer.feature.city.repository.model.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long>{
    CityEntity findByNameAndCountryCodeIgnoreCase(String name, String countryCode);
}
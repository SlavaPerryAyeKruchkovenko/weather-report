package dev.kruchkovenko.weatherproducer.config;

import dev.kruchkovenko.weatherproducer.feature.city.model.ParamCity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class AppConfig {

    @Value("#{'${countries.list}'.split(';')}")
    private List<String> countriesList;

    public List<ParamCity> getCountriesList() {
        if (this.countriesList == null || this.countriesList.isEmpty()) {
            return Collections.emptyList();
        }

        return this.countriesList.stream().map(city -> {
            var params = city.split(",");
            if (params.length < 2) {
                throw new IllegalArgumentException("Invalid city format: " + city);
            }
            return new ParamCity(params[0], params[1]);
        }).toList();
    }
}
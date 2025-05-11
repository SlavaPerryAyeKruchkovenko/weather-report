package dev.kruchkovenko.weatherproducer.feature.city.client;

import dev.kruchkovenko.weatherproducer.feature.city.client.model.CityResponse;
import dev.kruchkovenko.weatherproducer.feature.city.mapper.CityResponseMapper;
import dev.kruchkovenko.weatherproducer.feature.city.model.City;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CityWebClient {
    private static final Log log = LogFactory.getLog(CityWebClient.class);

    private final CityResponseMapper mapper;
    private final WebClient webClient;

    public CityWebClient(@Value("${city.url}") String baseUrl, CityResponseMapper mapper) {
        this.mapper = mapper;
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<City> searchCity(String name, String countryCode) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("search/")
                        .queryParam("name", name.toUpperCase())
                        .queryParam("count", 1)
                        .queryParam("language", "EN")
                        .queryParam("countryCode", countryCode.toUpperCase())
                        .build())
                .retrieve()
                .bodyToMono(CityResponse.class)
                .map(mapper::transform)
                .doOnError(e -> log.error("Failed to fetch city data", e.getCause()));
    }
}

package dev.kruchkovenko.weatherproducer.feature.city.client;

import dev.kruchkovenko.weatherproducer.feature.city.client.model.CityResponse;
import dev.kruchkovenko.weatherproducer.feature.city.mapper.CityResponseMapper;
import dev.kruchkovenko.weatherproducer.feature.city.model.City;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CityWebClient {
    @Value("${city.url}")
    @NotNull
    private String url;

    private final CityResponseMapper mapper;

    public CityWebClient(CityResponseMapper mapper){
        this.mapper = mapper;
    }

    private final WebClient webClient = WebClient.builder()
            .baseUrl(url)
            .build();

    public Mono<City> searchCity(String name, String countryCode) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search")
                        .queryParam("name", name)
                        .queryParam("count", 1)
                        .queryParam("language", "en")
                        .queryParam("format", "json")
                        .queryParam("countryCode", countryCode)
                        .build())
                .retrieve()
                .bodyToMono(CityResponse.class).map(mapper::transform);
    }
}

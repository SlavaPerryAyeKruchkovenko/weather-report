package dev.kruchkovenko.weatherproducer.feature.weather.client.yandex;

import dev.kruchkovenko.weatherproducer.feature.weather.client.yandex.model.YandexWeatherResponse;
import dev.kruchkovenko.weatherproducer.feature.weather.mapper.YandexWeatherResponseMapper;
import dev.kruchkovenko.weatherproducer.feature.weather.model.Weather;
import dev.kruchkovenko.weatherproducer.shared.Coordinate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class YandexWebClient {
    private static final Log log = LogFactory.getLog(YandexWebClient.class);

    private final YandexWeatherResponseMapper mapper;
    private final WebClient webClient;

    public YandexWebClient(@Value("${yandex.api}") String baseUrl, @Value("${yandex.api.key}") String apiKey, YandexWeatherResponseMapper mapper) {
        this.mapper = mapper;
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .defaultHeader("X-Yandex-API-Key", apiKey)
                .build();
    }

    public Mono<Weather> getWeather(Coordinate coordinate) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("forecast/")
                        .queryParam("lat", coordinate.latitude())
                        .queryParam("lon", coordinate.longitude())
                        .queryParam("lang", "EN")
                        .queryParam("limit", 1)
                        .build())
                .retrieve()
                .bodyToMono(YandexWeatherResponse.class)
                .map(response -> mapper.transform(response, LocalDateTime.now()))
                .doOnError(e -> log.error("Failed to fetch weather's data from Yandex", e.getCause()));
    }
}

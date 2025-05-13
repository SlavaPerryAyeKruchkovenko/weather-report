package dev.kruchkovenko.weatherproducer.feature.weather.client.meteo;

import dev.kruchkovenko.weatherproducer.feature.weather.client.meteo.model.MeteoWeatherResponse;
import dev.kruchkovenko.weatherproducer.feature.weather.mapper.MeteoWeatherResponseMapper;
import dev.kruchkovenko.weatherproducer.feature.weather.model.Weather;
import dev.kruchkovenko.weatherproducer.shared.Coordinate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class MeteoWebClient {
    private static final Log log = LogFactory.getLog(MeteoWebClient.class);

    private final MeteoWeatherResponseMapper mapper;
    private final WebClient webClient;

    public MeteoWebClient(@Value("${meteo.api}") String baseUrl, MeteoWeatherResponseMapper mapper) {
        this.mapper = mapper;
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<Weather> getWeather(Coordinate coordinate) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("forecast/")
                        .queryParam("latitude", coordinate.latitude())
                        .queryParam("longitude", coordinate.longitude())
                        .queryParam("current_weather", true)
                        .build())
                .retrieve()
                .bodyToMono(MeteoWeatherResponse.class)
                .map(response -> mapper.transform(response, LocalDateTime.now()))
                .doOnError(e -> log.error("Failed to fetch weather's data from Meteo", e.getCause()));
    }
}

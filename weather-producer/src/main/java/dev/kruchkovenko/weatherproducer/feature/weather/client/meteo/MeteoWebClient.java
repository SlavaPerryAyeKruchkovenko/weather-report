package dev.kruchkovenko.weatherproducer.feature.weather.client.meteo;

import dev.kruchkovenko.weatherproducer.feature.weather.client.meteo.model.MeteoWeatherResponse;
import dev.kruchkovenko.weatherproducer.shared.Coordinate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MeteoWebClient {
    private static final Log log = LogFactory.getLog(MeteoWebClient.class);

    private final WebClient webClient;

    public MeteoWebClient(@Value("${meteo.api}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<Double> getTemperature(Coordinate coordinate) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("forecast/")
                        .queryParam("latitude", coordinate.latitude())
                        .queryParam("longitude", coordinate.longitude())
                        .queryParam("current_weather", true)
                        .build())
                .retrieve()
                .bodyToMono(MeteoWeatherResponse.class)
                .map(response -> response.getCurrentWeather().getTemperature())
                .doOnError(e -> log.error("Failed to fetch weather's data from Meteo", e.getCause()));
    }
}

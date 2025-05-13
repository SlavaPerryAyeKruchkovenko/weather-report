package dev.kruchkovenko.weatherproducer.entry;

import dev.kruchkovenko.weatherproducer.config.AppConfig;
import dev.kruchkovenko.weatherproducer.feature.city.model.City;
import dev.kruchkovenko.weatherproducer.feature.city.model.ParamCity;
import dev.kruchkovenko.weatherproducer.feature.city.service.CityService;
import dev.kruchkovenko.weatherproducer.feature.weather.model.Weather;
import dev.kruchkovenko.weatherproducer.feature.weather.service.WeatherService;
import dev.kruchkovenko.weatherproducer.shared.Coordinate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class WeatherFetchJob {
    private static final Log log = LogFactory.getLog(WeatherFetchJob.class);
    private final WeatherService weatherService;
    private final CityService cityService;
    private final HashMap<ParamCity, Optional<Coordinate>> coordinates;

    public WeatherFetchJob(WeatherService weatherService, AppConfig appConfig, CityService cityService) {
        this.weatherService = weatherService;
        this.cityService = cityService;
        this.coordinates = appConfig.getCountriesList().stream()
                .collect(Collectors.toMap(
                        city -> city,
                        city -> Optional.empty(),
                        (existing, replacement) -> existing,
                        HashMap::new
                ));
    }

    @Scheduled(fixedRateString = "#{${server.interval.seconds} * 1000}")
    public void fetchAndStoreWeather() throws IllegalArgumentException {
        for (var param : coordinates.keySet()) {
            getWeatherByCity(param)
                    .doOnNext(weather -> log.info(String.format("Fetching weather for city: %s", param)))
                    .doOnNext(weatherService::saveWeather).subscribe();
        }
    }

    private Mono<Weather> getWeatherByCity(ParamCity city) {
        var coordinate = this.coordinates.get(city).orElse(null);
        if (coordinate == null) {
            coordinate = fetchCityCoordinate(city);
            this.coordinates.put(city, Optional.of(coordinate));
        }
        return weatherService
                .fetchTemperaturesByCoordinate(coordinate)
                .collectList()
                .flatMap(temps -> Mono.just(new Weather(
                        city.getCityName(),
                        city.getCountryCode(),
                        LocalDateTime.now(),
                        temps.stream().map(Weather.Forecast::new).toList())));
    }

    private Coordinate fetchCityCoordinate(ParamCity param) throws IllegalArgumentException {
        City city = cityService.getCity(param)
                .blockOptional()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("City %s not found", param.getCityName())));

        return Optional.ofNullable(city.getCoordinate())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Coordinate not found for city %s", param.getCityName())));
    }
}

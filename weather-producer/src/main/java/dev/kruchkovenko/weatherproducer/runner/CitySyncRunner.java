package dev.kruchkovenko.weatherproducer.runner;

import dev.kruchkovenko.weatherproducer.config.AppConfig;
import dev.kruchkovenko.weatherproducer.config.DatabaseConfig;
import dev.kruchkovenko.weatherproducer.feature.city.service.CityService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CitySyncRunner implements CommandLineRunner {
    private static final Log log = LogFactory.getLog(CitySyncRunner.class);
    private final CityService cityService;
    private final AppConfig appConfig;

    public CitySyncRunner(CityService cityService, AppConfig appConfig) {
        this.cityService = cityService;
        this.appConfig = appConfig;
    }

    @Override
    public void run(String... args) {
        cityService.syncCities(appConfig.getCountriesList())
                .doOnSubscribe(__ -> log.info("Starting cities sync..."))
                .doOnSuccess(__ -> log.info("Sync completed successfully"))
                .doOnError(ex -> log.error("Cities sync failed", ex.getCause()))
                .subscribe();
    }
}

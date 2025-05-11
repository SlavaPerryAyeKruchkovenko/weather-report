package dev.kruchkovenko.weatherproducer.runner;

import dev.kruchkovenko.weatherproducer.feature.city.service.CityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CitySyncRunner implements CommandLineRunner {
    private final CityService cityService;

    public CitySyncRunner(CityService cityService) {
        this.cityService = cityService;
    }
    @Override
    public void run(String... args) {
        System.out.println("In CommandLineRunnerImpl ");

        for (String arg : args) {
            System.out.println(arg);
        }
    }
}

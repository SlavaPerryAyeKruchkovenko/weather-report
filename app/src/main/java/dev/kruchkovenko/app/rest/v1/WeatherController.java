package dev.kruchkovenko.app.rest.v1;

import dev.kruchkovenko.app.feature.model.Weather;
import dev.kruchkovenko.app.feature.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/v1/weather")
@Tag(name = "Weather API", description = "Operations with weather data")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(
            value = "/",
            produces = APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Get weather by city and country",
            description = "Returns weather data for specified location",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    public ResponseEntity<List<Weather>> getAllWeather(
            @RequestParam(name = "city") String city,
            @RequestParam(name = "countryCode") String countryCode,
            @RequestParam(name = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        if (date == null) {
            var weathers = weatherService.getAllWeather(city, countryCode);
            return ResponseEntity.ok(weathers);
        }
        var weathers = weatherService.getAllWeather(city, countryCode, date);
        return ResponseEntity.ok(weathers);
    }
}

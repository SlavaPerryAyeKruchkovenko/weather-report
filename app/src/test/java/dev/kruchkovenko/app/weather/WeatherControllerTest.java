package dev.kruchkovenko.app.weather;

import dev.kruchkovenko.app.feature.model.Weather;
import dev.kruchkovenko.app.feature.service.WeatherService;
import dev.kruchkovenko.app.rest.v1.WeatherController;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    private final String TEST_CITY = "Moscow";
    private final String TEST_COUNTRY = "RU";
    private final LocalDate TEST_DATE = LocalDate.now();
    private final Weather TEST_WEATHER = new Weather(TEST_CITY, TEST_COUNTRY, LocalDateTime.now(), 21.0);

    @Test
    void getAllWeatherWithDate() {
        when(weatherService.getAllWeather(TEST_CITY, TEST_COUNTRY, TEST_DATE))
                .thenReturn(List.of(TEST_WEATHER));

        var response = weatherController.getAllWeather(
                TEST_CITY, TEST_COUNTRY, TEST_DATE);

        assertEquals(OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(TEST_WEATHER, response.getBody().get(0));
        verify(weatherService).getAllWeather(TEST_CITY, TEST_COUNTRY, TEST_DATE);
        verifyNoMoreInteractions(weatherService);
    }

    @Test
    void getAllWeatherWithoutDate() {
        when(weatherService.getAllWeather(TEST_CITY, TEST_COUNTRY))
                .thenReturn(List.of(TEST_WEATHER));

        var response = weatherController.getAllWeather(
                TEST_CITY, TEST_COUNTRY, null);

        assertEquals(OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        verify(weatherService).getAllWeather(TEST_CITY, TEST_COUNTRY);
        verifyNoMoreInteractions(weatherService);
    }

    @Test
    void getAllWeatherWithoutDataInDB() {
        when(weatherService.getAllWeather(TEST_CITY, TEST_COUNTRY))
                .thenReturn(Collections.emptyList());

        var response = weatherController.getAllWeather(
                TEST_CITY, TEST_COUNTRY, null);

        assertEquals(OK, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).isEmpty());
        verify(weatherService).getAllWeather(TEST_CITY, TEST_COUNTRY);
        verifyNoMoreInteractions(weatherService);
    }

    @Test
    void getAllWeather_serviceThrowsException_shouldReturn400() {
        var errorMessage = RandomStringUtils.random(10);
        when(weatherService.getAllWeather(TEST_CITY, TEST_COUNTRY))
                .thenThrow(new RuntimeException(errorMessage));

        var response = weatherController.getAllWeather(
                TEST_CITY, TEST_COUNTRY, null);

        assertEquals(BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(weatherService).getAllWeather(TEST_CITY, TEST_COUNTRY);
        verifyNoMoreInteractions(weatherService);
    }
}

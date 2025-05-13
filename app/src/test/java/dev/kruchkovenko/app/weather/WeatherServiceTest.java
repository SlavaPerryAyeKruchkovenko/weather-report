package dev.kruchkovenko.app.weather;

import dev.kruchkovenko.app.feature.mapper.AvgWeatherMapper;
import dev.kruchkovenko.app.feature.model.AvgWeather;
import dev.kruchkovenko.app.feature.model.Weather;
import dev.kruchkovenko.app.feature.repository.RedisWeatherRepository;
import dev.kruchkovenko.app.feature.service.WeatherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private RedisWeatherRepository repository;

    @Mock
    private AvgWeatherMapper mapper;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    private final String TEST_CITY = "Moscow";
    private final String TEST_COUNTRY = "RU";
    private final LocalDate TEST_DATE = LocalDate.now();
    private final LocalDateTime TEST_DATETIME = LocalDateTime.now();

    @Test
    void getAllWeatherWithDate() {
        var randomTemperature1 = new Random().nextDouble();
        var randomTemperature2 = new Random().nextDouble();
        var avgWeather1 = new AvgWeather("1", TEST_CITY, TEST_COUNTRY, TEST_DATETIME.minusDays(1), randomTemperature1);
        var avgWeather2 = new AvgWeather("2", TEST_CITY, TEST_COUNTRY, TEST_DATETIME, randomTemperature2);
        var weather2 = new Weather(TEST_CITY, TEST_COUNTRY, TEST_DATETIME, randomTemperature2);

        when(repository.findByCityAndCountryCode(TEST_CITY, TEST_COUNTRY))
                .thenReturn(List.of(avgWeather1, avgWeather2));
        when(mapper.transform(avgWeather2)).thenReturn(weather2);

        var result = weatherService.getAllWeather(TEST_CITY, TEST_COUNTRY, TEST_DATE);

        assertEquals(1, result.size());
        assertEquals(weather2, result.get(0));
        verify(repository).findByCityAndCountryCode(TEST_CITY, TEST_COUNTRY);
        verify(mapper).transform(avgWeather2);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void getAllWeatherWithoutDate() {
        var randomTemperature1 = new Random().nextDouble();
        var randomTemperature2 = new Random().nextDouble();
        var avgWeather1 = new AvgWeather("1", TEST_CITY, TEST_COUNTRY, TEST_DATETIME.minusDays(1), randomTemperature1);
        var avgWeather2 = new AvgWeather("2", TEST_CITY, TEST_COUNTRY, TEST_DATETIME, randomTemperature2);
        var weather2 = new Weather(TEST_CITY, TEST_COUNTRY, TEST_DATETIME, randomTemperature2);

        when(repository.findByCityAndCountryCode(TEST_CITY, TEST_COUNTRY))
                .thenReturn(List.of(avgWeather1, avgWeather2));
        when(mapper.transform(avgWeather2)).thenReturn(weather2);

        var result = weatherService.getAllWeather(TEST_CITY, TEST_COUNTRY);

        assertEquals(1, result.size());
        assertEquals(weather2, result.get(0));
        verify(repository).findByCityAndCountryCode(TEST_CITY, TEST_COUNTRY);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void getAllWeatherWithoutDataInDB() {
        when(repository.findByCityAndCountryCode(TEST_CITY, TEST_COUNTRY))
                .thenReturn(Collections.emptyList());

        var result = weatherService.getAllWeather(TEST_CITY, TEST_COUNTRY);

        assertTrue(result.isEmpty());
        verify(repository).findByCityAndCountryCode(TEST_CITY, TEST_COUNTRY);
        verifyNoInteractions(mapper);
        verifyNoMoreInteractions(repository);
    }
}

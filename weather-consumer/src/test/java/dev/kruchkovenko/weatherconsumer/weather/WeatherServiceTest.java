package dev.kruchkovenko.weatherconsumer.weather;

import dev.kruchkovenko.weatherconsumer.feature.weather.model.AvgWeather;
import dev.kruchkovenko.weatherconsumer.feature.weather.repository.RedisWeatherRepository;
import dev.kruchkovenko.weatherconsumer.feature.weather.service.WeatherServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;
import java.util.Random;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private RedisWeatherRepository repository;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @Test
    void saveAvgWeather() {
        var testWeather = new AvgWeather(
                RandomStringUtils.random(10),
                RandomStringUtils.random(10),
                RandomStringUtils.random(2),
                LocalDateTime.now(),
                new Random().nextDouble()
        );
        when(repository.save(testWeather)).thenAnswer((Answer<Void>) invocationOnMock -> null);

        weatherService.save(testWeather);

        verify(repository).save(testWeather);
        verifyNoMoreInteractions(repository);
    }
}

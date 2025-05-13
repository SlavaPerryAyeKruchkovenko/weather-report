package dev.kruchkovenko.weatherconsumer.entry;

import dev.kruchkovenko.weatherconsumer.config.RabbitConfig;
import dev.kruchkovenko.weatherconsumer.feature.weather.model.AvgWeather;
import dev.kruchkovenko.weatherconsumer.feature.weather.model.Weather;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WeatherConsumer {
    private static final Log log = LogFactory.getLog(WeatherConsumer.class);

    @RabbitListener(queues = RabbitConfig.WEATHER_QUEUE)
    public void listener(Weather weather) {
        if (weather != null && !weather.getForecasts().isEmpty()) {
            var forecasts = weather.getForecasts();
            var averageTemp = forecasts.stream()
                    .mapToDouble(Weather.Forecast::getTemperature)
                    .average()
                    .orElse(0.0);
            var avgWeather = new AvgWeather(weather.getCity(), weather.getCountryCode(), weather.getMeasureTime(), averageTemp);
            log.info(String.format("save weather %s", avgWeather));
        }
    }
}

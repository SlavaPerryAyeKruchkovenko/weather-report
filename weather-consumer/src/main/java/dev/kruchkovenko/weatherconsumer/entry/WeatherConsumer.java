package dev.kruchkovenko.weatherconsumer.entry;

import dev.kruchkovenko.weatherconsumer.config.RabbitConfig;
import dev.kruchkovenko.weatherconsumer.feature.weather.model.Weather;
import dev.kruchkovenko.weatherconsumer.feature.weather.service.WeatherService;
import dev.kruchkovenko.weatherconsumer.feature.weather.util.WeatherUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class WeatherConsumer {
    private static final Log log = LogFactory.getLog(WeatherConsumer.class);
    private final WeatherService service;

    public WeatherConsumer(WeatherService service) {
        this.service = service;
    }

    @RabbitListener(queues = RabbitConfig.WEATHER_QUEUE)
    public void listener(Weather weather) {
        var avgWeather = WeatherUtil.getAvgWeather(weather);
        service.save(avgWeather);
        log.info(String.format("save weather %s", avgWeather));
    }
}

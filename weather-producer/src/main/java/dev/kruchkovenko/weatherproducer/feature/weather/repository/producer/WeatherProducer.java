package dev.kruchkovenko.weatherproducer.feature.weather.repository.producer;

import dev.kruchkovenko.weatherproducer.feature.weather.model.Weather;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class WeatherProducer {
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    public WeatherProducer(RabbitTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }

    public void sendWeathers(Weather weather) {
        rabbitTemplate.convertAndSend(queue.getName(), weather);
    }
}

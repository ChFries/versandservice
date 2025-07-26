package prv.fries.versandservice.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableRabbit
@Slf4j
@Profile("RABBITMQ")
public class RabbitMQListenerConfiguration {

    public static final String QUEUE_ZAHLUNG_ABGESCHLOSSEN = "versand.zahlung.abgeschlossen.queue";
    public static final String ROUTING_KEY_ZAHLUNG_ABGESCHLOSSEN = "bestellung.zahlung.abgeschlossen";


    @Bean
    public Queue pruefungAbgeschlossenQueue() {
        return new Queue(QUEUE_ZAHLUNG_ABGESCHLOSSEN, true);
    }


    @Bean
    public Binding bindPruefungQueueToExchange(Queue pruefungAbgeschlossenQueue, TopicExchange bestellungExchange) {
        return BindingBuilder.bind(pruefungAbgeschlossenQueue)
                .to(bestellungExchange)
                .with(ROUTING_KEY_ZAHLUNG_ABGESCHLOSSEN);
    }
}

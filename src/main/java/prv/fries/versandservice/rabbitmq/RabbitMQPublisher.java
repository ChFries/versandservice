package prv.fries.versandservice.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import prv.fries.versandservice.generated.BestellungDto;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile("RABBITMQ")
public class RabbitMQPublisher {

    public static final String ROUTING_KEY_VERSAND_ABGESCHLOSSEN = "bestellung.versand.abgeschlossen";

    private final RabbitTemplate rabbitTemplate;

    public void publishVersandAbgeschlossen(BestellungDto bestellungDto) {
        rabbitTemplate.convertAndSend(ROUTING_KEY_VERSAND_ABGESCHLOSSEN, bestellungDto);
    }
}

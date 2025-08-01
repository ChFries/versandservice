package prv.fries.versandservice.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import prv.fries.versandservice.generated.BestellungDto;
import prv.fries.versandservice.service.VersandService;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile("RABBITMQ")
public class VersandServiceRabbit {

    private final RabbitMQPublisher rabbitMQPublisher;
    private final VersandService versandService;

    @RabbitListener(queues = RabbitMQListenerConfiguration.QUEUE_ZAHLUNG_ABGESCHLOSSEN)
    public void handlePruefungAbgeshchlossen(BestellungDto bestellungDto) {
        log.info("[RABBITMQ] Erstelle Versandauftag fuer Bestellung {}", bestellungDto.getId());
        var versendeteBestellugn = versandService.createVerstandauftrag(bestellungDto);
        rabbitMQPublisher.publishVersandAbgeschlossen(versendeteBestellugn);
    }

}

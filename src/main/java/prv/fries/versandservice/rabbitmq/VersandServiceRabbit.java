package prv.fries.versandservice.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import prv.fries.versandservice.generated.client.payment.BestellungDto;
import prv.fries.versandservice.generated.client.payment.StatusDto;
import prv.fries.versandservice.model.Person;
import prv.fries.versandservice.model.VersandStatus;
import prv.fries.versandservice.repository.VersandRepository;
import prv.fries.versandservice.service.StammdatenService;

@Service
@Slf4j
@RequiredArgsConstructor
public class VersandServiceRabbit {

    private final VersandRepository versandRepository;
    private final StammdatenService stammdatenService;
    private final RabbitMQPublisher rabbitMQPublisher;

    @RabbitListener(queues = RabbitMQListenerConfiguration.QUEUE_ZAHLUNG_ABGESCHLOSSEN)
    public void handlePruefungAbgeshchlossen(BestellungDto bestellungDto) {
        log.info("Hamlo {}", bestellungDto);
        prv.fries.versandservice.entity.Versandauftrag auftrag = new prv.fries.versandservice.entity.Versandauftrag();
        auftrag.setKundenId(bestellungDto.getKundeId());
        auftrag.setBestellungId(bestellungDto.getId());
        auftrag.setVersandStatus(VersandStatus.ERFASST);
        auftrag = versandRepository.save(auftrag);

        Person person = stammdatenService.getPersonZuKunde(bestellungDto.getKundeId());
        log.info("Person {} found for KundenId {}", person.toString(), bestellungDto.getKundeId());
        auftrag.setSendungsnummer("TRX-" + person.hashCode());
        auftrag.setVersandStatus(VersandStatus.VERSENDET);
        versandRepository.save(auftrag);

        bestellungDto.setStatus(StatusDto.VERSENDET);

        rabbitMQPublisher.publishVersandAbgeschlossen(bestellungDto);
    }

}

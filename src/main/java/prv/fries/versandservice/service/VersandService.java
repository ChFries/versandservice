package prv.fries.versandservice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import prv.fries.versandservice.entity.Versandauftrag;
import prv.fries.versandservice.generated.BestellungDto;
import prv.fries.versandservice.generated.StatusDto;
import prv.fries.versandservice.model.Person;
import prv.fries.versandservice.model.VersandStatus;
import prv.fries.versandservice.repository.VersandRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class VersandService {

    private final VersandRepository versandRepository;
    private final StammdatenService stammdatenService;


    public BestellungDto createVerstandauftrag(BestellungDto bestellungDto) {
        Versandauftrag auftrag = new Versandauftrag();
        auftrag.setKundenId(bestellungDto.getKundeId());
        auftrag.setBestellungId(bestellungDto.getId());
        auftrag.setVersandStatus(VersandStatus.ERFASST);
        auftrag = versandRepository.save(auftrag);

        Person person = stammdatenService.getPersonZuKunde(bestellungDto.getKundeId());
        auftrag.setSendungsnummer("TRX-" + person.hashCode());
        auftrag.setVersandStatus(VersandStatus.VERSENDET);
        versandRepository.save(auftrag);

        bestellungDto.setStatus(StatusDto.VERSENDET);
        return bestellungDto;
    }

}

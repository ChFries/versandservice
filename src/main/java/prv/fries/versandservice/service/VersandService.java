package prv.fries.versandservice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import prv.fries.versandservice.generated.VersandauftragDto;
import prv.fries.versandservice.generated.VersandauftragRequest;
import prv.fries.versandservice.mapper.VersandauftragMapper;
import prv.fries.versandservice.model.Person;
import prv.fries.versandservice.model.VersandStatus;
import prv.fries.versandservice.repository.VersandRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class VersandService {

    private final VersandRepository versandRepository;
    private final StammdatenService stammdatenService;
    private final VersandauftragMapper versandauftragMapper;

    public VersandauftragDto createVersandauftrag(VersandauftragRequest versandauftragRequest) {


        prv.fries.versandservice.entity.Versandauftrag auftrag = new prv.fries.versandservice.entity.Versandauftrag();
        auftrag.setKundenId(versandauftragRequest.getKundenId());
        auftrag.setBestellungId(versandauftragRequest.getBestellungId());
        auftrag.setVersandStatus(VersandStatus.ERFASST);

        auftrag = versandRepository.save(auftrag);

        Person person = stammdatenService.getPersonZuKunde(versandauftragRequest.getKundenId());
        log.info("Person {} found for KundenId {}", person.toString(), versandauftragRequest.getKundenId());

        auftrag.setSendungsnummer("TRX-" + person.hashCode());
        auftrag.setVersandStatus(VersandStatus.VERSENDET);

        auftrag = versandRepository.save(auftrag);

        return versandauftragMapper.toDto(auftrag);
    }

}

package prv.fries.versandservice.rest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.VersandApi;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import prv.fries.versandservice.generated.BestellungDto;
import prv.fries.versandservice.service.VersandService;

@RestController
@RequiredArgsConstructor
@Slf4j
@Profile("REST")
public class VersandController implements VersandApi {

    private final VersandService versandService;

    @Override
    public ResponseEntity<BestellungDto> versandPost(BestellungDto bestellungDto) {
        log.info("[REST] Erstelle Versandauftag fuer Bestellung {}", bestellungDto.getId());
        var versandAuftragCompleted = versandService.createVerstandauftrag(bestellungDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(versandAuftragCompleted);
    }
}

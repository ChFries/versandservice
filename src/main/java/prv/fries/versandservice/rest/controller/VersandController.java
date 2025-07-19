package prv.fries.versandservice.rest.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.VersandApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import prv.fries.versandservice.generated.VersandauftragDto;
import prv.fries.versandservice.generated.VersandauftragRequest;
import prv.fries.versandservice.service.VersandService;

@RestController
@RequiredArgsConstructor
public class VersandController implements VersandApi {

    private final VersandService versandService;

    @Override
    public ResponseEntity<VersandauftragDto> versandPost(VersandauftragRequest versandauftragRequest) {

        var versandAuftragCompleted = versandService.createVersandauftrag(versandauftragRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(versandAuftragCompleted);
    }
}

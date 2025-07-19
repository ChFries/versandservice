package prv.fries.versandservice.service;

import org.springframework.stereotype.Service;
import prv.fries.versandservice.model.Person;

import java.util.UUID;

@Service
public interface StammdatenService {

    Person getPersonZuKunde(UUID kundenId);
}

package prv.fries.versandservice.service;

import org.springframework.stereotype.Service;
import prv.fries.versandservice.model.Adresse;
import prv.fries.versandservice.model.Person;

import java.util.Random;
import java.util.UUID;

@Service
public class StammdatenMockService implements StammdatenService {

    private static final String[] VORNAMEN = {"Anna", "Max", "Lena", "Paul", "Mia", "Tom"};
    private static final String[] NACHNAMEN = {"Müller", "Schmidt", "Schneider", "Fischer", "Weber", "Wagner"};
    private static final String[] STRASSEN = {"Hauptstraße", "Bahnhofstraße", "Gartenweg", "Schulstraße", "Bergweg"};

    private final Random random = new Random();

    @Override
    public Person getPersonZuKunde(UUID kundenId) {
        String name = randomFromArray(VORNAMEN);
        String nachname = randomFromArray(NACHNAMEN);
        String strasse = randomFromArray(STRASSEN);
        int hausnummer = 1 + random.nextInt(200);
        int plz = 10000 + random.nextInt(89999);

        Adresse adresse = new Adresse(strasse, hausnummer, plz);
        return new Person(name, nachname, adresse);
    }

    private String randomFromArray(String[] array) {
        return array[random.nextInt(array.length)];
    }
}

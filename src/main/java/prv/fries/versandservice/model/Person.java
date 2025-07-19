package prv.fries.versandservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Person {
    private String name;
    private String nachname;
    private Adresse adresse;

}
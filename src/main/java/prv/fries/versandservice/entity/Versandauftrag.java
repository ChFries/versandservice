package prv.fries.versandservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import prv.fries.versandservice.model.VersandStatus;

import java.util.UUID;

@Entity
@Getter
@Setter
@Data
public class Versandauftrag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private VersandStatus versandStatus;

    @Column(nullable = false)
    private UUID kundenId;

    @Column(nullable = false)
    private UUID bestellungId;

    @Column
    private String sendungsnummer;



}

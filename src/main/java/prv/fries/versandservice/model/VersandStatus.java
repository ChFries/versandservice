package prv.fries.versandservice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum VersandStatus {
    VERSENDET("versendet"),
    ERFASST("erfasst");

    private final String value;
}

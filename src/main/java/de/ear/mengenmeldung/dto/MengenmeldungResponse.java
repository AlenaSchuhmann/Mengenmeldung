package de.ear.mengenmeldung.dto;

import de.ear.mengenmeldung.entity.MengenmeldungBE;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MengenmeldungResponse(
        Long id,
        BigDecimal menge,
        int einheit,
        int jahr,
        int monat,
        int geraeteartNummer,
        int registrierungsnummer,
        LocalDateTime erstelltAm
) {
    public static MengenmeldungResponse toResponse(MengenmeldungBE entity) {
        return new MengenmeldungResponse(
                entity.getId(),
                entity.getMenge(),
                entity.getEinheit(),
                entity.getJahr(),
                entity.getMonat(),
                entity.getGeraeteartNummer(),
                entity.getRegistrierungsnummer(),
                entity.getErstelltAm()
                );
    }
}

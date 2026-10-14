package de.ear.mengenmeldung.dto;

import de.ear.mengenmeldung.entity.MengenmeldungBE;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MengenmeldungResponse(
        Long id,
        String geraeteart,
        BigDecimal menge,
        String status,
        LocalDateTime erstelltAm,
        String fehler
) {
    public static MengenmeldungResponse from(MengenmeldungBE entity) {
        return new MengenmeldungResponse(
                entity.id,
                entity.geraeteart,
                entity.menge,
                entity.status.name(),
                entity.erstelltAm,
                entity.fehler
        );
    }
}

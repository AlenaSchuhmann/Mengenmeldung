package de.ear.mengenmeldung.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record MengenmeldungRequest(
        @NotNull(message = "Die Menge ist erforderlich")
        @Positive(message = "Die Menge muss größer als 0 sein")
        BigDecimal menge,

        @NotNull(message = "Einheit darf nicht leer sein")
        int einheit,

        @NotNull(message = "Jahr darf nicht leer sein")
        int jahr,

        @NotNull(message = "Monat darf nicht leer sein")
        int monat,

        @NotNull(message = "Geräteart darf nicht leer sein")
        int geraeteartNummer,

        @NotNull(message = "Registrierungsnummer darf nicht leer sein")
        int registrierungsnummer,

        @AssertTrue(message = "Bestätigung zur Richtigkeit der Angaben fehlt ")
        boolean wahrheitserklaerung

) {}

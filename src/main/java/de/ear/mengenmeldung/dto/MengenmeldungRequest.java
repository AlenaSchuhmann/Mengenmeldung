package de.ear.mengenmeldung.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record MengenmeldungRequest(
        @NotBlank(message = "Die Geräteart darf nicht leer sein")
        String geraeteart,

        @NotNull(message = "Die Menge ist erforderlich")
        @Positive(message = "Die Menge muss größer als 0 sein")
        BigDecimal menge
) {}

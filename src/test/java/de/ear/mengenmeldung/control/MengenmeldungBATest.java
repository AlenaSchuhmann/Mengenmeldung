package de.ear.mengenmeldung.control;

import de.ear.mengenmeldung.dto.MengenmeldungRequest;
import de.ear.mengenmeldung.dto.MengenmeldungResponse;
import de.ear.mengenmeldung.entity.MengenmeldungBE;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class MengenmeldungBATest {

    @Inject
    MengenmeldungBA mengenmeldungBA;

    @Test
    @TestTransaction
    void submitMengenmeldung_shouldPersistAndReturnMengenmeldung() {
        // Arrange
        MengenmeldungRequest request = validRequest();

        // Act
        MengenmeldungResponse response =
                mengenmeldungBA.submitMengenmeldung(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.id());
        assertEquals(0, new BigDecimal("125.50")
                .compareTo(response.menge()));
        assertEquals(request.einheit(), response.einheit());
        assertEquals(request.jahr(), response.jahr());
        assertEquals(request.monat(), response.monat());
        assertEquals(
                request.geraeteartNummer(),
                response.geraeteartNummer()
        );
        assertEquals(
                request.registrierungsnummer(),
                response.registrierungsnummer()
        );
        assertNotNull(response.erstelltAm());

        MengenmeldungBE persisted =
                MengenmeldungBE.findById(response.id());

        assertNotNull(persisted);
    }

    @Test
    @TestTransaction
    void getAllMengenmeldungen_shouldReturnPersistedMengenmeldungen() {
        // Arrange
        MengenmeldungResponse created =
                mengenmeldungBA.submitMengenmeldung(validRequest());

        // Act
        List<MengenmeldungResponse> responses =
                mengenmeldungBA.getAllMengenmeldungen();

        // Assert
        assertNotNull(responses);
        assertTrue(responses.stream()
                .anyMatch(response -> response.id().equals(created.id())));

        MengenmeldungResponse found = responses.stream()
                .filter(response -> response.id().equals(created.id()))
                .findFirst()
                .orElseThrow();

        assertEquals(
                created.registrierungsnummer(),
                found.registrierungsnummer()
        );
        assertEquals(0, created.menge().compareTo(found.menge()));
    }

    private MengenmeldungRequest validRequest() {
        return new MengenmeldungRequest(
                new BigDecimal("125.50"),
                1,
                2026,
                10,
                100,
                12345,
                true
        );
    }
}
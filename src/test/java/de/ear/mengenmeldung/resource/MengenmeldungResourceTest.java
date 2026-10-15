package de.ear.mengenmeldung.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import de.ear.mengenmeldung.boundary.MengenmeldungBF;
import de.ear.mengenmeldung.dto.MengenmeldungRequest;
import de.ear.mengenmeldung.dto.MengenmeldungResponse;
import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
class MengenmeldungResourceTest {

    private static final String BASE_PATH = "/api/mengenmeldungen";

    @InjectMock
    MengenmeldungBF mengenmeldungBF;

    @Test
    void submitMengenmeldung_shouldReturnMengenmeldung() {
        // Arrange
        MengenmeldungRequest request = validRequest();

        MengenmeldungResponse response = new MengenmeldungResponse(
                1L,
                new BigDecimal("125.50"),
                1,
                2026,
                10,
                100,
                12345,
                LocalDateTime.of(2026, 10, 15, 12, 0)
        );

        when(mengenmeldungBF.submitMengenmeldung(any(MengenmeldungRequest.class)))
                .thenReturn(response);

        // Act & Assert
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request)
                .when()
                .post(BASE_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("menge", equalTo(125.50f))
                .body("einheit", equalTo(1))
                .body("jahr", equalTo(2026))
                .body("monat", equalTo(10))
                .body("geraeteartNummer", equalTo(100))
                .body("registrierungsnummer", equalTo(12345));

        verify(mengenmeldungBF)
                .submitMengenmeldung(any(MengenmeldungRequest.class));
    }

    @Test
    void getAllMengenmeldungen_shouldReturnList() {
        // Arrange
        List<MengenmeldungResponse> responses = List.of(
                new MengenmeldungResponse(
                        1L,
                        new BigDecimal("125.50"),
                        1,
                        2026,
                        10,
                        100,
                        12345,
                        LocalDateTime.of(2026, 10, 15, 12, 0)
                ),
                new MengenmeldungResponse(
                        2L,
                        new BigDecimal("200.00"),
                        2,
                        2026,
                        9,
                        200,
                        67890,
                        LocalDateTime.of(2026, 9, 10, 10, 30)
                )
        );

        when(mengenmeldungBF.getAllMengenmeldungen())
                .thenReturn(responses);

        // Act & Assert
        given()
                .accept(ContentType.JSON)
                .when()
                .get(BASE_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(2))
                .body("[0].id", equalTo(1))
                .body("[0].menge", equalTo(125.50f))
                .body("[1].id", equalTo(2))
                .body("[1].menge", equalTo(200.0f));

        verify(mengenmeldungBF).getAllMengenmeldungen();
    }

    @Test
    void submitMengenmeldung_shouldReturnBadRequestWhenMengeIsInvalid() {
        // Menge darf nicht null oder kleiner/gleich 0 sein.
        String invalidRequest = """
                {
                    "menge": -10,
                    "einheit": 1,
                    "jahr": 2026,
                    "monat": 10,
                    "geraeteartNummer": 100,
                    "registrierungsnummer": 12345,
                    "wahrheitserklaerung": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(invalidRequest)
                .when()
                .post(BASE_PATH)
                .then()
                .statusCode(400);
    }

    @Test
    void submitMengenmeldung_shouldReturnBadRequestWhenDeclarationIsFalse() {
        String invalidRequest = """
                {
                    "menge": 100,
                    "einheit": 1,
                    "jahr": 2026,
                    "monat": 10,
                    "geraeteartNummer": 100,
                    "registrierungsnummer": 12345,
                    "wahrheitserklaerung": false
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(invalidRequest)
                .when()
                .post(BASE_PATH)
                .then()
                .statusCode(400);
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

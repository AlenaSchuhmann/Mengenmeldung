package de.ear.mengenmeldung.resource;

import de.ear.external.client.EarSoapClient;
import de.ear.mengenmeldung.boundary.MengenmeldungBF;
import de.ear.mengenmeldung.dto.MengenmeldungRequest;
import de.ear.mengenmeldung.dto.MengenmeldungResponse;
import de.ear_system.ear_soap.v2.StandardResponse;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Path("/api/mengenmeldungen")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MengenmeldungResource {

    @Context
    UriInfo uriInfo;

    @Inject
    EarSoapClient earSoapClient;

    @Inject
    private MengenmeldungBF mengenmeldungBF;

    @POST
    @Path("/submit-ist-input")
    public Response submitIstInput() {
        try {
            StandardResponse result = earSoapClient.submitIstInput(
                    new BigDecimal("10.23"),
                    3,          // 3 = Tonnen
                    2026,       // Meldejahr
                    1,          // Januar
                    56,         // Geräteartnummer
                    12345678,   // Test-Registrierungsnummer
                    true        // Wahrheitserklärung
            );

            return Response.ok(Map.of(
                    "code", result.getCode(),
                    "text", result.getText()
            )).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", e.getMessage()))
                    .build();

        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_GATEWAY)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

    @POST
    @Transactional
    public MengenmeldungResponse submitMengenmeldung(@Valid MengenmeldungRequest request) {
        return mengenmeldungBF.submitMengenmeldung(request);

/*        URI location = uriInfo.getAbsolutePathBuilder()
                .path(meldung.getId().toString())
                .build();

        return Response.created(location)
                .entity(response)
                .build();*/
    }

    @GET
    public List<MengenmeldungResponse> getAllMengenmeldungen() {
        return mengenmeldungBF.getAllMengenmeldungen();
    }

    @GET
    @Path("/{id}")
    public MengenmeldungResponse getMengenmeldung(@PathParam("id") Long id) {
        return mengenmeldungBF.getMengenmeldung(id);
    }
}

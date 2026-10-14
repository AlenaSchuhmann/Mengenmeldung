package de.ear.mengenmeldung.resource;

import de.ear.mengenmeldung.dto.MengenmeldungRequest;
import de.ear.mengenmeldung.dto.MengenmeldungResponse;
import de.ear.mengenmeldung.entity.MengenmeldungBE;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

@Path("/api/mengenmeldungen")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MengenmeldungResource {

    @Context
    UriInfo uriInfo;

    @POST
    @Transactional
    public Response createMengenmeldung(@Valid MengenmeldungRequest request) {
        MengenmeldungBE meldung = new MengenmeldungBE();
        meldung.geraeteart = request.geraeteart();
        meldung.menge = request.menge();
        meldung.status = MengenmeldungBE.Status.PENDING;

        meldung.persist();

        MengenmeldungResponse response =
                MengenmeldungResponse.from(meldung);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(meldung.id.toString())
                .build();

        return Response.created(location)
                .entity(response)
                .build();
    }

    @GET
    public List<MengenmeldungResponse> getAllMengenmeldungen() {
        return MengenmeldungBE.<MengenmeldungBE>listAll()
                .stream()
                .map(MengenmeldungResponse::from)
                .toList();
    }

    @GET
    @Path("/{id}")
    public MengenmeldungResponse getMengenmeldung(
            @PathParam("id") Long id) {

        MengenmeldungBE meldung =
                MengenmeldungBE.findById(id);

        if (meldung == null) {
            throw new NotFoundException(
                    "Mengenmeldung mit ID " + id + " nicht gefunden"
            );
        }

        return MengenmeldungResponse.from(meldung);
    }
}

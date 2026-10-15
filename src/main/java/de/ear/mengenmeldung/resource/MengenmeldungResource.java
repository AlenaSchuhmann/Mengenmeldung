package de.ear.mengenmeldung.resource;

import de.ear.mengenmeldung.boundary.MengenmeldungBF;
import de.ear.mengenmeldung.dto.MengenmeldungRequest;
import de.ear.mengenmeldung.dto.MengenmeldungResponse;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api/mengenmeldungen")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MengenmeldungResource {

    @Inject
    private MengenmeldungBF mengenmeldungBF;

    @POST
    @Transactional
    public MengenmeldungResponse submitMengenmeldung(@Valid MengenmeldungRequest request) {
        return mengenmeldungBF.submitMengenmeldung(request);
    }

    @GET
    public List<MengenmeldungResponse> getAllMengenmeldungen() {
        return mengenmeldungBF.getAllMengenmeldungen();
    }

}

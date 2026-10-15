package de.ear.mengenmeldung.boundary;

import de.ear.mengenmeldung.control.MengenmeldungBA;
import de.ear.mengenmeldung.dto.MengenmeldungRequest;
import de.ear.mengenmeldung.dto.MengenmeldungResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class MengenmeldungBF {

    @Inject
    private MengenmeldungBA mengenmeldungBA;

    public MengenmeldungResponse submitMengenmeldung(MengenmeldungRequest mengenmeldungRequest) {
        return mengenmeldungBA.submitMengenmeldung(mengenmeldungRequest);
    }

    public List<MengenmeldungResponse> getAllMengenmeldungen() {
        return mengenmeldungBA.getAllMengenmeldungen();
    }
}

package de.ear.mengenmeldung.control;

import de.ear.mengenmeldung.dto.MengenmeldungRequest;
import de.ear.mengenmeldung.dto.MengenmeldungResponse;
import de.ear.mengenmeldung.entity.MengenmeldungBE;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class MengenmeldungBA {

    private Log logger;

    @Inject
    private MengenmeldungMapper mapper;

    public MengenmeldungResponse submitMengenmeldung(MengenmeldungRequest mengenmeldungRequest) {
        validateInput(mengenmeldungRequest);
        MengenmeldungBE meldung = mapper.toEntity(mengenmeldungRequest);
        meldung.persistAndFlush();
        return MengenmeldungResponse.toResponse(meldung);
    }

    public MengenmeldungResponse getMengenmeldung(Long id) {
        MengenmeldungBE meldung =
                MengenmeldungBE.findById(id);

        if (meldung == null) {
            throw new NotFoundException("Mengenmeldung with ID " + id + " not found.");
        }
        return MengenmeldungResponse.toResponse(meldung);
    }

    public List<MengenmeldungResponse> getAllMengenmeldungen() {
        return MengenmeldungBE.<MengenmeldungBE>listAll()
                .stream()
                .map(MengenmeldungResponse::toResponse)
                .toList();
    }

    private void validateInput(MengenmeldungRequest mengenmeldungRequest) {
        Log.infof("Validation started");
        //ToDo: validate input for einheit, Nachkommastellen bei menge, valid jahr und monat
        //Geraeteartnummer und registrierungsnummer
    }
}

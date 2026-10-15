package de.ear.mengenmeldung.control;

import de.ear.mengenmeldung.dto.MengenmeldungRequest;
import de.ear.mengenmeldung.entity.MengenmeldungBE;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MengenmeldungMapper {

    public MengenmeldungBE toEntity(MengenmeldungRequest request) {
        MengenmeldungBE meldung = new MengenmeldungBE();

        meldung.setGeraeteartNummer(request.geraeteartNummer());
        meldung.setMenge(request.menge());
        meldung.setJahr(request.jahr());
        meldung.setMonat(request.monat());
        meldung.setRegistrierungsnummer(request.registrierungsnummer());
        meldung.setEinheit(request.einheit());
        meldung.setWahrheitserklaerung(request.wahrheitserklaerung());

        return meldung;
    }
}

package de.ear.external.client;

import de.ear.technical.EarSoapException;
import de.ear_system.ear_soap.v2.MitteilungsServicePort;
import de.ear_system.ear_soap.v2.ServiceException;
import de.ear_system.ear_soap.v2.StandardResponse;
import io.quarkiverse.cxf.annotation.CXFClient;
import jakarta.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

import java.math.BigDecimal;

@ApplicationScoped
public class EarSoapClient {

    private static final Logger LOG =
            Logger.getLogger(EarSoapClient.class);

    @CXFClient("ear")
    MitteilungsServicePort port;

    public StandardResponse submitIstInput(
            BigDecimal menge,
            int einheit,
            int jahr,
            int monat,
            int geraeteartnummer,
            int registrierungsnummer,
            boolean wahrheitserklaerung) {

        if (menge == null || menge.signum() < 0) {
            throw new IllegalArgumentException(
                    "Die Menge muss angegeben werden und darf nicht negativ sein.");
        }

        if (einheit != 1 && einheit != 3) {
            throw new IllegalArgumentException(
                    "Ungültige Einheit. Erlaubt sind 1 (kg) und 3 (t).");
        }

        if (monat < 1 || monat > 12) {
            throw new IllegalArgumentException(
                    "Der Monat muss zwischen 1 und 12 liegen.");
        }

        if (!wahrheitserklaerung) {
            throw new IllegalArgumentException(
                    "Die Wahrheitserklärung muss bestätigt werden.");
        }

        try {
            LOG.info("Übermittle Ist-Inputmitteilung an EAR.");

            StandardResponse response = port.submitIstInput(
                    menge,
                    einheit,
                    jahr,
                    monat,
                    geraeteartnummer,
                    registrierungsnummer,
                    true
            );

            LOG.infof(
                    "EAR-Antwort: Code=%d, Text=%s",
                    response.getCode(),
                    response.getText()
            );

            return response;

        } catch (ServiceException e) {
            LOG.error("EAR hat die SOAP-Anfrage mit einer ServiceException abgelehnt.", e);
            throw new EarSoapException(
                    "Die EAR-Schnittstelle hat die Meldung abgelehnt.", e);

        } catch (RuntimeException e) {
            LOG.error("Technischer Fehler beim SOAP-Aufruf an EAR.", e);
            throw new EarSoapException(
                    "Die EAR-Schnittstelle ist momentan nicht erreichbar.", e);
        }
    }
}

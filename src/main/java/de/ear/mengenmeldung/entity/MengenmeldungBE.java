package de.ear.mengenmeldung.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "mengen_meldung")
public class MengenmeldungBE extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int geraeteartNummer;

    @Column(nullable = false)
    private BigDecimal menge;

    @Column(nullable = false)
    private int einheit;

    @Column(nullable = false)
    private int jahr;

    @Column(nullable = false)
    private int monat;

    @Column(nullable = false)
    private int registrierungsnummer;

    @Column(nullable = false)
    private boolean wahrheitserklaerung;

    @Column(nullable = false)
    private LocalDateTime erstelltAm = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGeraeteartNummer() {
        return geraeteartNummer;
    }

    public void setGeraeteartNummer(int geraeteartNummer) {
        this.geraeteartNummer = geraeteartNummer;
    }

    public BigDecimal getMenge() {
        return menge;
    }

    public void setMenge(BigDecimal menge) {
        this.menge = menge;
    }

    public int getEinheit() {
        return einheit;
    }

    public void setEinheit(int einheit) {
        this.einheit = einheit;
    }

    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public int getMonat() {
        return monat;
    }

    public void setMonat(int monat) {
        this.monat = monat;
    }

    public int getRegistrierungsnummer() {
        return registrierungsnummer;
    }

    public void setRegistrierungsnummer(int registrierungsnummer) {
        this.registrierungsnummer = registrierungsnummer;
    }

    public boolean isWahrheitserklaerung() {
        return wahrheitserklaerung;
    }

    public void setWahrheitserklaerung(boolean wahrheitserklaerung) {
        this.wahrheitserklaerung = wahrheitserklaerung;
    }

    public LocalDateTime getErstelltAm() {
        return erstelltAm;
    }

    public void setErstelltAm(LocalDateTime erstelltAm) {
        this.erstelltAm = erstelltAm;
    }
}

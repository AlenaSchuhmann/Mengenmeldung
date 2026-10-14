package de.ear.mengenmeldung.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "mengen_meldung")
public class MengenmeldungBE extends PanacheEntity {

    @Column(nullable = false)
    public String geraeteart;

    @Column(nullable = false, precision = 15, scale = 3)
    public BigDecimal menge;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Status status = Status.PENDING;

    @Column(nullable = false)
    public LocalDateTime erstelltAm = LocalDateTime.now();

    public String fehler;

    public enum Status {
        PENDING,
        SUBMITTED,
        FAILED
    }
}

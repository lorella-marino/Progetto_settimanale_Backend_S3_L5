package it.epicode.progettosettimanale.prestiti;

import it.epicode.progettosettimanale.elementi_catalogo.ElementoCatalogo;
import it.epicode.progettosettimanale.utenti.Utente;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor

@Entity
@Table(name = "prestiti")

@NamedQuery(name = "PrestitoFindScadutiNonRestituiti", query = "SELECT e FROM ElementoCatalogo e WHERE e.prestito IS NOT NULL AND e.prestito.dataRestituzioneEffettiva IS NULL AND e.prestito.dataRestituzionePrevista < CURRENT_DATE")
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Utente utente;

    @OneToOne
    private ElementoCatalogo elementoPrestato;

    @Column(nullable = false)
    private LocalDate dataInizioPrestito;
    private LocalDate dataRestituzionePrevista;
    private LocalDate dataRestituzioneEffettiva;

    public Prestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = calcolaDataRestituzionePrevista();
    }

    private LocalDate calcolaDataRestituzionePrevista() {
        return (dataInizioPrestito != null) ? dataInizioPrestito.plusDays(30) : null;
    }

    public Prestito(Utente utente, ElementoCatalogo elementoPrestato, LocalDate dataInizioPrestito, LocalDate dataRestituzioneEffettiva) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = calcolaDataRestituzionePrevista();
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

}


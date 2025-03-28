package it.epicode.progettosettimanale.elementi_catalogo;

import it.epicode.progettosettimanale.prestiti.Prestito;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor

@Entity
@Table(name = "elementiCatalogo")
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@NamedQuery(name = "ElementoFindByIsbn", query = "SELECT e FROM ElementoCatalogo e WHERE e.isbn = :isbn")
@NamedQuery(name = "ElementoFindByAnnoPubblicazione", query = "SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :annoPubblicazione")
@NamedQuery(name = "ElementoFindByAutore", query = "SELECT e FROM Libro e WHERE e.autore = :autore")
@NamedQuery(name = "ElementoFindByTitolo", query = "SELECT e FROM ElementoCatalogo e WHERE e.titolo LIKE :titolo")
@NamedQuery(name = "ElementoFindElementiInPrestito", query = "SELECT e FROM ElementoCatalogo e WHERE e.prestito IS NOT NULL AND e.prestito.dataRestituzioneEffettiva IS NULL AND e.prestito.utente.numeroTessera = :numeroTessera")
@NamedQuery(name = "ElementoFindPrestitiScadutiNonRestituiti", query = "SELECT e FROM ElementoCatalogo e WHERE e.prestito IS NOT NULL AND e.prestito.dataRestituzioneEffettiva IS NULL AND e.prestito.dataRestituzionePrevista < CURRENT_DATE")

public abstract class ElementoCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long isbn;

    @Column (length = 150, nullable = false)
    private String titolo;

    private int annoPubblicazione;

    private int numeroPagine;

    @OneToOne(mappedBy = "elementoPrestato")
    private Prestito prestito;

    public ElementoCatalogo(Long isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public String toString() {
        return "ElementoBiblioteca{" +
                "isbn=" + isbn +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}

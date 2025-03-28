package it.epicode.progettosettimanale.elementi_catalogo.rivista;

import it.epicode.progettosettimanale.elementi_catalogo.ElementoCatalogo;
import it.epicode.progettosettimanale.elementi_catalogo.rivista.periodicita.Periodicita;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
public class Rivista extends ElementoCatalogo {
    @Enumerated  (EnumType.STRING)
    private Periodicita periodicita;

    public Rivista(Long isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "isbn=" + getIsbn() +
                ", titolo='" + getTitolo() + '\'' +
                ", periodicita=" + periodicita +
                ", annoPubblicazione=" + getAnnoPubblicazione() +
                ", numeroPagine=" + getNumeroPagine() +
                '}';
    }
}

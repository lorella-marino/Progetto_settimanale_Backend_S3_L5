package it.epicode.progettosettimanale.utenti;

import it.epicode.progettosettimanale.prestiti.Prestito;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name = "utenti")

public class Utente {
    @Id
    @GeneratedValue (strategy = jakarta.persistence.GenerationType.SEQUENCE)
    private Long numeroTessera;

    @Column (length = 100, nullable = false)
    private String nome;

    @Column (length = 100, nullable = false)
    private String cognome;

    private LocalDate dataNascita;

    @OneToMany(mappedBy = "utente")
    private List <Prestito> prestiti;

    public Utente(Long numeroTessera, String nome, String cognome, LocalDate dataNascita) {
        this.numeroTessera = numeroTessera;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }
}

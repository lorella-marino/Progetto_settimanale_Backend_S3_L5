package it.epicode.progettosettimanale;

import it.epicode.progettosettimanale.elementi_catalogo.ElementoCatalogo;
import it.epicode.progettosettimanale.elementi_catalogo.ElementoCatalogoDAO;
import it.epicode.progettosettimanale.elementi_catalogo.libro.Libro;
import it.epicode.progettosettimanale.elementi_catalogo.rivista.Rivista;
import it.epicode.progettosettimanale.elementi_catalogo.rivista.periodicita.Periodicita;
import it.epicode.progettosettimanale.prestiti.Prestito;
import it.epicode.progettosettimanale.prestiti.PrestitoDAO;
import it.epicode.progettosettimanale.utenti.Utente;
import it.epicode.progettosettimanale.utenti.UtenteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("epicode");
        EntityManager em = emf.createEntityManager();

        ElementoCatalogoDAO elementoCatalogoDAO = new ElementoCatalogoDAO(em);
        PrestitoDAO prestitoDAO = new PrestitoDAO(em);
        UtenteDAO  utenteDAO = new UtenteDAO(em);

        ElementoCatalogo libro1 = new Libro(null, "Il Signore degli Anelli", 1954, 1178, "J.R.R. Tolkien", "Fantasy");
        ElementoCatalogo libro2 = new Libro(null, "1984", 1949, 328, "George Orwell", "Distopia");
        ElementoCatalogo libro3 = new Libro(null, "Il Nome della Rosa", 1980, 504, "Umberto Eco", "Romanzo");
        ElementoCatalogo rivista1 = new Rivista (null, "National Geographic", 1888, 150, Periodicita.SEMESTRALE);
        ElementoCatalogo rivista2 = new Rivista (null, "Time", 1923, 100, Periodicita.SETTIMANALE);
        ElementoCatalogo rivista3 = new Rivista (null, "Vanity Fair", 1924, 80, Periodicita.MENSILE);

        Utente  utente1 = new Utente(null, "Mario", "Rossi", LocalDate.of(1990, 5, 15));
        Utente  utente2 = new Utente(null, "Luigi", "Verdi", LocalDate.of(1985, 8, 20));

        Prestito  prestito1 = new Prestito(utente1, libro1, LocalDate.of(2025, 3, 2), null);
        Prestito  prestito2 = new Prestito(utente1, libro2, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 29));
        Prestito  prestito3 = new Prestito(utente2, libro3, LocalDate.of(2024, 9, 3),  LocalDate.of(2025, 2, 28));
        Prestito  prestito4 = new Prestito(utente2, rivista1, LocalDate.of(2025, 1, 4), null);



        em.getTransaction ().begin();

        //AGGIUNTA ELEMENTO
        elementoCatalogoDAO.insert(libro1);
        elementoCatalogoDAO.insert(libro2);
        elementoCatalogoDAO.insert(libro3);
        elementoCatalogoDAO.insert(rivista1);
        elementoCatalogoDAO.insert(rivista2);
        elementoCatalogoDAO.insert(rivista3);

        utenteDAO.insert(utente1);
        utenteDAO.insert(utente2);
        prestitoDAO.insert(prestito1);
        prestitoDAO.insert(prestito2);
        prestitoDAO.insert(prestito3);
        prestitoDAO.insert(prestito4);

        //RIMOZIONE ELEMENTO tramite ISBN
        elementoCatalogoDAO.delete(6L);

        em.getTransaction ().commit();

        System.out.println();
        System.out.println("-- Find by ISBN --");
        List<ElementoCatalogo> elementi1 = elementoCatalogoDAO.getElementoFindByIsbn(3L);
        elementi1.forEach(System.out::println);

        System.out.println();
        System.out.println("-- Find by Anno di Pubblicazione --");
        List<ElementoCatalogo> elementi2 =  elementoCatalogoDAO.getElementoFindByAnnoPubblicazione(1888);
        elementi2.forEach(System.out::println);

        System.out.println();
        System.out.println("-- Find by Autore --");
        List<ElementoCatalogo> elementi3 =  elementoCatalogoDAO.getElementoFindByAutore("J.R.R. Tolkien");
        elementi3.forEach(System.out::println);

        System.out.println();
        System.out.println("-- Find by Titolo, anche parziale --");
        List<ElementoCatalogo> elementi4 =  elementoCatalogoDAO.getElementoFindByTitolo("National");
        elementi4.forEach(System.out::println);

        System.out.println();
        System.out.println("-- Find elementi in prestito tramite numero tessera--");
        List<ElementoCatalogo> elementi5 =  elementoCatalogoDAO.getElementoFindInPrestito(2L);
        elementi5.forEach(System.out::println);

        System.out.println();
        System.out.println("-- Find prestiti scaduti e non restituiti--");
        List<Prestito> elementi6 =  elementoCatalogoDAO.getPrestitoFindScadutiNonRestituiti();
        elementi6.forEach(System.out::println);


        em.close();
        emf.close();

    }
}

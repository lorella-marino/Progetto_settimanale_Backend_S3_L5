package it.epicode.progettosettimanale.elementi_catalogo;
import it.epicode.progettosettimanale.prestiti.Prestito;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ElementoCatalogoDAO {
    private final EntityManager em;

    public ElementoCatalogoDAO(EntityManager em) {
        this.em = em;
    }

    public void insert(ElementoCatalogo elementoCatalogo ) {
        em.persist(elementoCatalogo );
        System.out.println("ElementoCatalogo salvato correttamente!");
    }

    public void update(ElementoCatalogo elementoCatalogo ) {
        em.merge(elementoCatalogo );
        System.out.println("ElementoCatalogo modificato correttamente!");
    }

    public ElementoCatalogo find(Long isbn ) {
        return em.find(ElementoCatalogo.class, isbn);
    }

    public void delete(Long isbn) {
        ElementoCatalogo a = find(isbn);
        em.remove(a);
        System.out.println("ElementoCatalogo eliminato correttamente!");
    }

    public List<ElementoCatalogo> getElementoFindByIsbn(Long isbn) {
        return em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.isbn = :isbn", ElementoCatalogo.class)
                .setParameter("isbn", isbn)
                .getResultList();
    }

    public List<ElementoCatalogo> getElementoFindByAnnoPubblicazione(int annoPubblicazione) {
        return em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :annoPubblicazione", ElementoCatalogo.class)
                .setParameter("annoPubblicazione", annoPubblicazione)
                .getResultList();
    }

    public List<ElementoCatalogo> getElementoFindByAutore(String autore) {
        return em.createQuery("SELECT e FROM Libro e WHERE e.autore = :autore", ElementoCatalogo.class)
                .setParameter("autore", autore)
                .getResultList();
    }

    public List<ElementoCatalogo> getElementoFindByTitolo(String titolo) {
        return em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.titolo LIKE :titolo", ElementoCatalogo.class)
                .setParameter("titolo", "%" + titolo + "%")
                .getResultList();
    }

    public List<ElementoCatalogo> getElementoFindInPrestito(Long numeroTessera) {
        return em.createQuery("SELECT p.elementoPrestato FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera AND p.dataRestituzioneEffettiva IS NULL", ElementoCatalogo.class)
                .setParameter("numeroTessera", numeroTessera)
                .getResultList();
    }

    public List<Prestito> getPrestitoFindScadutiNonRestituiti () {
        return em.createQuery("SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva IS NULL AND p.dataRestituzionePrevista < CURRENT_DATE", Prestito.class)
                .getResultList();
    }
}

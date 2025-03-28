package it.epicode.progettosettimanale.utenti;

import jakarta.persistence.EntityManager;

public class UtenteDAO {
    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void insert(Utente utente ) {
        em.persist(utente );
        System.out.println("Utente salvato correttamente!");
    }

    public void update(Utente utente ) {
        em.merge(utente );
        System.out.println("Utente modificato correttamente!");
    }

    public Utente find(Long id) {
        return em.find(Utente.class, id);
    }

    public void delete(Long id) {
        Utente a = find(id);
        em.remove(a);
        System.out.println("Utente eliminato correttamente!");
    }
}

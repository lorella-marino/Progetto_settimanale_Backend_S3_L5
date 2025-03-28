package it.epicode.progettosettimanale.prestiti;

import jakarta.persistence.EntityManager;

public class PrestitoDAO {
    private final EntityManager em;

    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    public void insert(Prestito prestito ) {
        em.persist(prestito );
        System.out.println("Prestito salvato correttamente!");
    }

    public void update(Prestito prestito ) {
        em.merge(prestito );
        System.out.println("Prestito modificato correttamente!");
    }

    public Prestito find(Long id) {
        return em.find(Prestito.class, id);
    }

    public void delete(Long id) {
        Prestito a = find(id);
        em.remove(a);
        System.out.println("Prestito eliminato correttamente!");
    }
}

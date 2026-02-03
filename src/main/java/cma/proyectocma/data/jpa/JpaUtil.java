package cma.proyectocma.data.jpa;

import cma.proyectocma.data.jpa.common.C;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JpaUtil {

    private static EntityManagerFactory emf;

    private static EntityManagerFactory getEmf() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(C.JPA_PERSISTENCEUNITNAME);
        }
        return emf;
    }

    private static EntityManager getEntityManager() {
        return getEmf().createEntityManager();
    }

    public static <R> R execute(Function<EntityManager, R> action) {
        try (EntityManager em = getEntityManager()) {
            return action.apply(em);
        }
    }

    public static <R> R executeTransaction(Function<EntityManager, R> action) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            R result = action.apply(em);
            em.getTransaction().commit();
            return result;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

}

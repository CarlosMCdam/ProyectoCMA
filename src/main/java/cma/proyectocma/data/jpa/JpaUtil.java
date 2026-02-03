package cma.proyectocma.data.jpa;

import cma.proyectocma.data.jpa.common.C;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Function;

/**
 * Proveedor del EntityManager para funciones que lo usan.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JpaUtil {

    /**
     * Clase factory del EntityManager.
     */
    private static EntityManagerFactory emf;

    /**
     * Getter con seguridad de nulo e instanciación del EntityManager.
     *
     * @return EntityManager
     */
    private static EntityManager getEntityManager() {
        if (emf == null) emf = Persistence.createEntityManagerFactory(C.JPA_PERSISTENCEUNITNAME);
        return emf.createEntityManager();
    }

    /**
     * Ejecuta una función que utiliza el EntityManager.
     *
     * @param action Función
     * @param <R>    Tipo de retorno de la función
     * @return Resultado de la función
     */
    public static <R> R execute(Function<EntityManager, R> action) {
        try (EntityManager em = getEntityManager()) {
            return action.apply(em);
        }
    }

    /**
     * Ejecuta una función transaccional que utiliza el EntityManager.
     *
     * @param action Función
     * @param <R>    Tipo de retorno de la función
     * @return Resultado de la función
     */
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

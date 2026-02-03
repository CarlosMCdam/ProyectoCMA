package cma.proyectocma.data.repository;

import cma.proyectocma.data.jpa.JpaUtil;
import cma.proyectocma.data.model.base.Entity;
import cma.proyectocma.data.repository.common.C;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio universal para entidades JPA.
 *
 * @param entityClass Clase de la entidad.
 * @param <E>         Entidad.
 * @param <Id>        Identificador de la entidad.
 */

@SuppressWarnings("java:S119")
public record Repository<E extends Entity, Id extends Serializable>(Class<E> entityClass) {

    /**
     * Buscar una entidad por identificador.
     *
     * @param id Identificador
     * @return Optional de la entidad.
     */
    public Optional<E> findById(Id id) {
        return JpaUtil.execute(em -> Optional.ofNullable(em.find(entityClass, id)));
    }

    /**
     * Buscar todas las entidades.
     *
     * @return List de las entidades.
     */
    public List<E> findAll() {
        return JpaUtil.execute(em ->
                em.createQuery(C.QUERY_FROM + entityClass.getSimpleName(), entityClass).getResultList()
        );
    }

    /**
     * Guardar o actualizar una entidad.
     *
     * @param entity Entidad.
     * @return Entidad.
     */
    public E save(E entity) {
        return JpaUtil.executeTransaction(em -> em.merge(entity));
    }

    /**
     * Borrar una entidad por identificador.
     *
     * @param entity Entidad.
     * @return Si se borró correctamente.
     */
    public boolean delete(E entity) {
        return JpaUtil.executeTransaction(em -> {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            return true;
        });
    }

    public E getReference(Id id) {
        return JpaUtil.execute(em -> em.getReference(entityClass, id));
    }

}


package cma.proyectocma.data.repository;

import cma.proyectocma.data.jpa.JpaUtil;
import cma.proyectocma.data.model.base.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("java:S119")
public record Repository<E extends Entity, Id extends Serializable>(Class<E> entityClass) {

    public Optional<E> findById(Id id) {
        return JpaUtil.execute(em -> Optional.ofNullable(em.find(entityClass, id)));
    }

    public List<E> findAll() {
        return JpaUtil.execute(em -> em.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList());
    }

    public E save(E entity) {
        return JpaUtil.executeTransaction(em -> em.merge(entity));
    }

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


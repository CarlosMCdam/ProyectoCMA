package cma.proyectocma.domain.mapper.util;

import cma.proyectocma.data.model.base.Entity;
import cma.proyectocma.data.model.base.EntityPkDoble;
import cma.proyectocma.data.repository.Repository;

import java.io.Serializable;

@SuppressWarnings("java:S119")
public record IdResolver<E extends Entity>(Class<E> entityClass) {

    public E resolve(Integer id) {
        return new Repository<E, Integer>(entityClass).getReference(id);
    }

    public E resolve(EntityPkDoble.PkDoble id) {
        return new Repository<E, EntityPkDoble.PkDoble>(entityClass).getReference(id);
    }

}

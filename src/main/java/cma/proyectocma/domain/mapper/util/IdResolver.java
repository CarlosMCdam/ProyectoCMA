package cma.proyectocma.domain.mapper.util;

import cma.proyectocma.data.model.base.Entity;
import cma.proyectocma.data.model.base.EntityPkDoble;
import cma.proyectocma.data.repository.Repository;

/**
 * Crea instancias del repositorio para devolver una entidad JPA referenciada por un identificador.
 * @param entityClass Clase de la Entidad.
 * @param <E> Entidad.
 */
public record IdResolver<E extends Entity>(Class<E> entityClass) {

    /**
     * Devuelve una entidad simple referenciada por el identificador.
     * @param entityClass Clase de la Entidad.
     * @param <E> Entidad.
     */
    public E resolve(Integer id) {
        return new Repository<E, Integer>(entityClass).getReference(id);
    }

    /**
     * Devuelve una entidad compuesta referenciada por el identificador.
     * @param entityClass Clase de la Entidad.
     * @param <E> Entidad.
     */
    public E resolve(EntityPkDoble.PkDoble id) {
        return new Repository<E, EntityPkDoble.PkDoble>(entityClass).getReference(id);
    }

}

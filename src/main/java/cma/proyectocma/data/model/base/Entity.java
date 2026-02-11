package cma.proyectocma.data.model.base;

import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@SuppressWarnings("java:S119")

/**
 * Superclase semántica de todas las entidades.
 */
@MappedSuperclass
public abstract sealed class Entity<Id extends Serializable> permits EntityPkSimple, EntityPkDoble {

    public abstract Id getId();

    public abstract void setId(Id id);

}

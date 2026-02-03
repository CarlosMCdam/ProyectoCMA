package cma.proyectocma.data.model.base;

import jakarta.persistence.MappedSuperclass;

@SuppressWarnings("java:S119")

/**
 * Superclase semántica de todas las entidades.
 */
@MappedSuperclass
public abstract sealed class Entity permits EntityPkSimple, EntityPkDoble {
}

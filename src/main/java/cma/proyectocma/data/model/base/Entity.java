package cma.proyectocma.data.model.base;

import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@SuppressWarnings("java:S119")

@MappedSuperclass
public abstract sealed class Entity permits EntityPkSimple, EntityPkDoble {
}

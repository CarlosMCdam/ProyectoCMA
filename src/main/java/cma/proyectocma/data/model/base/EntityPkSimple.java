package cma.proyectocma.data.model.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 * Superclase de todas las entidades con clave primaria simple.
 */

@Getter
@Setter

@MappedSuperclass
public abstract non-sealed class EntityPkSimple extends Entity {

    /**
     * Clave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

}

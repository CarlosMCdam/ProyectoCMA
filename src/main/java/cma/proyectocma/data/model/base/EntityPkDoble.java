package cma.proyectocma.data.model.base;

import cma.proyectocma.data.model.common.C;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

/**
 * Superclase de todas las entidades con clave primaria doble.
 */
@Getter
@Setter

@MappedSuperclass
public abstract non-sealed class EntityPkDoble extends Entity<EntityPkDoble.PkDoble> {

    /**
     * Clave primaria.
     */
    @EmbeddedId
    private PkDoble id;

    /**
     * Clase que define el tipo de la clave primaria.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor

    @Embeddable
    public static class PkDoble implements Serializable {

        /**
         * Primer identificador de la clave primaria.
         */
        @Column(name = C.ENTITY_DOBLE_ID_1, nullable = false)
        private Integer id1;

        /**
         * Segundo identificador de la clave primaria.
         */
        @Column(name = C.ENTITY_DOBLE_ID_2, nullable = false)
        private Integer id2;

        /**
         * Override del método equals.
         * @param o Objeto de referencia con el que comparar.
         * @return Si ambos identificadores de ambas claves son iguales.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
            PkDoble pkDoble = (PkDoble) o;
            return Objects.equals(this.id1, pkDoble.getId1()) &&
                    Objects.equals(this.id2, pkDoble.getId2());
        }

        /**
         * Override del método hashCode.
         * @return Ambos identificadores hasheados.
         */
        @Override
        public int hashCode() {
            return Objects.hash(id2, id1);
        }

    }

}

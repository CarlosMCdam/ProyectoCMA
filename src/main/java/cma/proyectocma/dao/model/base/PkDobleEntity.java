package cma.proyectocma.dao.model.base;

import cma.proyectocma.dao.model.common.C;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter

@MappedSuperclass
public abstract class PkDobleEntity {

    @EmbeddedId
    private PkDoble id;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor

    @Embeddable
    public static class PkDoble implements Serializable {
        @Column(name = C.ENTITY_DOBLE_ID_1, nullable = false)
        private Integer id1;

        @Column(name = C.ENTITY_DOBLE_ID_2, nullable = false)
        private Integer id2;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
            PkDoble entity = (PkDoble) o;
            return Objects.equals(this.id1, entity.getId1()) &&
                    Objects.equals(this.id2, entity.getId2());
        }

        @Override
        public int hashCode() {
            return Objects.hash(id2, id1);
        }
    }

}

package cma.proyectocma.dao.model.base;

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
public abstract class EntityPkDoble {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor

    @Embeddable
    public static class PkDoble implements Serializable {
        @Column(name = "id_1", nullable = false)
        private Integer id1;

        @Column(name = "id_2", nullable = false)
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

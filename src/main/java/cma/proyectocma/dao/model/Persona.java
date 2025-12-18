package cma.proyectocma.dao.model;

import cma.proyectocma.dao.model.base.EntityPkSimple;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Persona", schema = "databaseCMA", uniqueConstraints = {
        @UniqueConstraint(name = "gmail", columnNames = {"gmail"})
})
@AttributeOverride(name = "id", column = @Column(name = "id_persona"))
public class Persona extends EntityPkSimple {

    @Column(name = "gmail", nullable = false, length = 100)
    private String gmail;

}
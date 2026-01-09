package cma.proyectocma.dao.model;

import cma.proyectocma.dao.model.base.EntityPkSimple;
import cma.proyectocma.dao.model.common.C;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = C.PERSONA_NOMBRE, schema = C.BBDD, uniqueConstraints = {
        @UniqueConstraint(name = C.PERSONA_UNIQUE_GMAIL, columnNames = {C.PERSONA_CAMPO_GMAIL})
})
@AttributeOverride(name = C.ENTITY_SIMPLE_ID, column = @Column(name = C.PERSONA_PK))
public class Persona extends EntityPkSimple {

    @Column(name = C.PERSONA_CAMPO_GMAIL, nullable = false, length = 100)
    private String gmail;

}
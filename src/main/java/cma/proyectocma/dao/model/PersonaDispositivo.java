package cma.proyectocma.dao.model;

import cma.proyectocma.dao.model.base.EntityPkDoble;
import cma.proyectocma.dao.model.common.C;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = C.PERSONADISPOSITIVO_NOMBRE, schema = C.BBDD)
@AttributeOverrides({
        @AttributeOverride(name = C.ENTITY_DOBLE_ID_1, column = @Column(name = C.PERSONADISPOSITIVO_PK_PERSONA)),
        @AttributeOverride(name = C.ENTITY_DOBLE_ID_2, column = @Column(name = C.PERSONADISPOSITIVO_PK_DISPOSITIVO))
})
public class PersonaDispositivo extends EntityPkDoble {

    @MapsId("id_1")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = C.PERSONADISPOSITIVO_PK_PERSONA, nullable = false)
    private Persona persona;

    @MapsId("id_2")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = C.PERSONADISPOSITIVO_PK_DISPOSITIVO, nullable = false)
    private Dispositivo dispositivo;

}
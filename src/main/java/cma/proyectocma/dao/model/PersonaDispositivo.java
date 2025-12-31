package cma.proyectocma.dao.model;

import cma.proyectocma.dao.model.base.EntityPkDoble;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Persona_Dispositivos", schema = "databaseCMA")
@AttributeOverrides({
        @AttributeOverride(name = "id.id_1", column = @Column(name = "id_persona")),
        @AttributeOverride(name = "id.id_2", column = @Column(name = "id_dispositivo"))
})
public class PersonaDispositivo extends EntityPkDoble {

    @MapsId("id_1")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona idPersona;

    @MapsId("id_2")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_dispositivo", nullable = false)
    private Dispositivo idDispositivo;

}
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
@Table(name = C.ARMARIOCARGA_NOMBRE, schema = C.BBDD)
@AttributeOverride(name = C.ENTITY_SIMPLE_ID, column = @Column(name = C.ARMARIOCARGA_PK))
public class ArmarioCarga extends EntityPkSimple {

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = C.ARMARIOCARGA_PK, nullable = false)
    private Dispositivo dispositivo;

    @Column(name = C.AMRARIOCARGA_CAMPO_NUMPUERTOS)
    private Integer numPuertos;

    @Column(name = C.AMRARIOCARGA_CAMPO_VENTILADO)
    private Boolean ventilado;

}
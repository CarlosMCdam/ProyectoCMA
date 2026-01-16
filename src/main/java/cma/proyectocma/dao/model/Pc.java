package cma.proyectocma.dao.model;

import cma.proyectocma.dao.model.base.PkSimpleEntity;
import cma.proyectocma.dao.model.common.C;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = C.PC_NOMBRE, schema = C.BBDD)
@AttributeOverride(name = C.ENTITY_SIMPLE_ID, column = @Column(name = C.PC_PK))
public class Pc extends PkSimpleEntity {

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = C.PC_PK, nullable = false)
    private Dispositivo dispositivo;

    @Column(name = C.PC_CAMPO_TIPODISCO, length = 30)
    private String tipoDisco;

    @Column(name = C.PC_CAMPO_RAMGB)
    private Integer ramGb;

}
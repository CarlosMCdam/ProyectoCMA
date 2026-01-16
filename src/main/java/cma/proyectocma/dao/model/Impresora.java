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
@Table(name = C.IMPRESORA_NOMBRE, schema = C.BBDD)
@AttributeOverride(name = C.ENTITY_SIMPLE_ID, column = @Column(name = C.IMPRESORA_PK))
public class Impresora extends PkSimpleEntity {

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = C.IMPRESORA_PK, nullable = false)
    private Dispositivo dispositivo;

    @Column(name = C.IMPRESORA_CAMPO_TIPOIMPRESION, length = 50)
    private String tipoImpresion;

    @Column(name = C.IMPRESORA_CAMPO_COLOR)
    private Boolean color;

}
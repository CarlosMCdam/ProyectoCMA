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
@Table(name = C.PANTALLATACTIL_NOMBRE, schema = C.BBDD)
@AttributeOverride(name = C.ENTITY_SIMPLE_ID, column = @Column(name = C.PANTALLATACTIL_PK))
public class PantallaTactil extends PkSimpleEntity {

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = C.PANTALLATACTIL_PK, nullable = false)
    private Dispositivo dispositivo;

    @Column(name = C.PANTALLATACTIL_CAMPO_PULGADAS, precision = 4, scale = 1)
    private java.math.BigDecimal pulgadas;

    @Column(name = C.PANTALLATACTIL_CAMPO_RESOLUCION, length = 50)
    private String resolucion;

}
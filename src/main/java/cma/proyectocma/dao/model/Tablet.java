package cma.proyectocma.dao.model;

import cma.proyectocma.dao.model.base.EntityPkSimple;
import cma.proyectocma.dao.model.common.C;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = C.TABLET_NOMBRE, schema = C.BBDD)
public class Tablet extends EntityPkSimple {

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = C.TABLET_PK, nullable = false)
    private Dispositivo dispositivo;

    @Column(name = C.TABLET_CAMPO_SISTEMAOPERATIVO, length = 50)
    private String sistemaOperativo;

    @Column(name = C.TABLET_CAMPO_PULGADAS, precision = 4, scale = 1)
    private BigDecimal pulgadas;

}
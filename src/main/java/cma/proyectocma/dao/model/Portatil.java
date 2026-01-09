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
@Table(name = C.PORTATIL_NOMBRE, schema = C.BBDD)
public class Portatil extends EntityPkSimple {

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = C.PORTATIL_PK, nullable = false)
    private Pc pc;

    @Column(name = C.PORTATIL_CAMPO_PULGADAS, precision = 4, scale = 1)
    private BigDecimal pulgadas;

}
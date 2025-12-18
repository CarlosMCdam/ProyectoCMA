package cma.proyectocma.dao.model;

import cma.proyectocma.dao.model.base.EntityPkSimple;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Portatiles", schema = "databaseCMA")
public class Portatil extends EntityPkSimple {

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pc", nullable = false)
    private Pc pc;

    @Column(name = "pulgadas", precision = 4, scale = 1)
    private BigDecimal pulgadas;

}
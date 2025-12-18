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
@Table(name = "Tablets", schema = "databaseCMA")
public class Tablet extends EntityPkSimple {

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_dispositivo", nullable = false)
    private Dispositivo dispositivos;

    @Column(name = "sistema_operativo", length = 50)
    private String sistemaOperativo;

    @Column(name = "pulgadas", precision = 4, scale = 1)
    private BigDecimal pulgadas;

}
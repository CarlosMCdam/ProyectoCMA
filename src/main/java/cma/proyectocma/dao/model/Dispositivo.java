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
@Table(name = C.DISPOSITIVO_NOMBRE, schema = C.BBDD)
@AttributeOverride(name = C.ENTITY_SIMPLE_ID, column = @Column(name = C.DISPOSITIVO_PK))
public class Dispositivo extends EntityPkSimple {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = C.DISPOSITIVO_CAMPO_IDMODELO, nullable = false)
    private Modelo modelo;

    @Column(name = C.DISPOSITIVO_CAMPO_UBICACION, nullable = false, length = 100)
    private String ubicacion;

}
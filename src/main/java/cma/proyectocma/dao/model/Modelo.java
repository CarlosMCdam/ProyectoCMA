package cma.proyectocma.dao.model;

import cma.proyectocma.dao.model.base.EntityPkSimple;
import cma.proyectocma.dao.model.common.C;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = C.MODELO_NOMBRE, schema = C.BBDD)
@AttributeOverride(name = C.ENTITY_SIMPLE_ID, column = @Column(name = C.MODELO_PK))
public class Modelo extends EntityPkSimple {

    @Column(name = C.MODELO_CAMPO_NOMBRE, nullable = false, length = 100)
    private String nombre;

    @Column(name = C.MODELO_CAMPO_ESTADO, length = 50)
    private String estado;

    @Column(name = C.MODELO_CAMPO_TIPO, length = 50)
    private String tipo;

    @Column(name = C.MODELO_CAMPO_DESCRIPCION)
    private String descripcion;

}
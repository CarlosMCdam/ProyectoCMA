package cma.proyectocma.dao.model;

import cma.proyectocma.dao.model.base.PkSimpleEntity;
import cma.proyectocma.dao.model.common.C;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = C.AVERIA_NOMBRE, schema = C.BBDD)
@AttributeOverride(name = C.ENTITY_SIMPLE_ID, column = @Column(name = C.AVERIA_PK))
public class Averia extends PkSimpleEntity {

    @Column(name = C.AVERIA_CAMPO_DESCRIPCION, nullable = false)
    private String descripcion;

    @Column(name = C.AVERIA_CAMPO_ESTADO, nullable = false, length = 50)
    private String estado;

    @Column(name = C.AVERIA_CAMPO_SOLUCION)
    private String solucion;

    @Column(name = C.AVERIA_CAMPO_FECHAINICIAL, nullable = false)
    private LocalDate fechaInicial;

    @Column(name = C.AVERIA_CAMPO_FECHAFINAL)
    private LocalDate fechaFinal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = C.AVERIA_CAMPO_IDDISPOSITIVO, nullable = false)
    private Dispositivo dispositivo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = C.AVERIA_CAMPO_IDPERSONA, nullable = false)
    private Persona persona;

}
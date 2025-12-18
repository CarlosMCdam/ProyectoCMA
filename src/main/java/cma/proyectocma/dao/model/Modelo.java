package cma.proyectocma.dao.model;

import cma.proyectocma.dao.model.base.EntityPkSimple;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "Modelo", schema = "databaseCMA")
@AttributeOverride(name = "id", column = @Column(name = "id_modelo"))
public class Modelo extends EntityPkSimple {

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "descripcion")
    private String descripcion;

}
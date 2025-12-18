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
@Table(name = "Dispositivos", schema = "databaseCMA")
@AttributeOverride(name = "id", column = @Column(name = "id_dispositivo"))
public class Dispositivo extends EntityPkSimple {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_modelo", nullable = false)
    private Modelo idModelo;

    @Column(name = "ubicacion", nullable = false, length = 100)
    private String ubicacion;

}
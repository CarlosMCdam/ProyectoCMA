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
@Table(name = "PC", schema = "databaseCMA")
@AttributeOverride(name = "id", column = @Column(name = "id_dispositivo"))
public class Pc extends EntityPkSimple {

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_dispositivo", nullable = false)
    private Dispositivo dispositivos;

    @Column(name = "tipo_disco", length = 30)
    private String tipoDisco;

    @Column(name = "ram_gb")
    private Integer ramGb;

}
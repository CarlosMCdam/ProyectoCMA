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
@Table(name = "Armarios_Carga", schema = "databaseCMA")
@AttributeOverride(name = "id", column = @Column(name = "id_dispositivo"))
public class ArmarioCarga extends EntityPkSimple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_dispositivo", nullable = false)
    private Dispositivo dispositivos;

    @Column(name = "num_puertos")
    private Integer numPuertos;

    @Column(name = "ventilado")
    private Boolean ventilado;

}
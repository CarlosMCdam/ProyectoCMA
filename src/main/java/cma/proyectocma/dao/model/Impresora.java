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
@Table(name = "Impresoras", schema = "databaseCMA")
@AttributeOverride(name = "id", column = @Column(name = "id_dispositivo"))
public class Impresora extends EntityPkSimple {

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_dispositivo", nullable = false)
    private Dispositivo dispositivos;

    @Column(name = "tipo_impresion", length = 50)
    private String tipoImpresion;

    @Column(name = "color")
    private Boolean color;

}
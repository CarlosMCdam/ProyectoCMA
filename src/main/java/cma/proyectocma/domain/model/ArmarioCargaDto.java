package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.IdReference;

public record ArmarioCargaDto(
        @DtoId
        @IdReference(IdReference.Entity.DISPOSITIVO)
        Integer idDispositivo,
        Integer numPuertos,
        Boolean ventilado
) {
}

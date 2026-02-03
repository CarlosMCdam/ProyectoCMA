package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.IdReference;

public record PcDto(
        @DtoId
        @IdReference(IdReference.Entity.DISPOSITIVO)
        Integer idDispositivo,
        String tipoDisco,
        Integer ramGb
) {
}

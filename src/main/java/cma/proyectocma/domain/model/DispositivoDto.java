package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.IdReference;

public record DispositivoDto(
        @DtoId
        Integer id,
        @IdReference(IdReference.Entity.MODELO)
        Integer idModelo,
        String ubicacion
) {
}

package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.IdReference;

public record ImpresoraDto(
        @DtoId
        Integer id,
        @IdReference(IdReference.Entity.DISPOSITIVO)
        Integer idDispositivo,
        String tipoImpresion,
        Boolean color
) {
}

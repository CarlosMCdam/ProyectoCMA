package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.IdReference;

import java.math.BigDecimal;

public record PantallaTactilDto(
        @DtoId
        @IdReference(IdReference.Entity.DISPOSITIVO)
        Integer idDispositivo,
        BigDecimal pulgadas,
        String resolucion
) {
}

package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.IdReference;

import java.math.BigDecimal;

public record PortatilDto(
        @DtoId
        @IdReference(IdReference.Entity.PC)
        Integer idPc,
        BigDecimal pulgadas
) {
}

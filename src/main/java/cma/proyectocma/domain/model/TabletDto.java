package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.IdReference;

import java.math.BigDecimal;

public record TabletDto(
        @DtoId
        @IdReference(IdReference.Entity.DISPOSITIVO)
        Integer idDispositivo,
        String sistemaOperativo,
        BigDecimal pulgadas
) {
}

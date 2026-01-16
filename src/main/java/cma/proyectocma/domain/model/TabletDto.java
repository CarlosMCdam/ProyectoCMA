package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.EntityReference;

import java.math.BigDecimal;

@EntityReference("Tablet")
public record TabletDto(
        @DtoId
        Integer id,
        String sistemaOperativo,
        BigDecimal pulgadas
) {
}

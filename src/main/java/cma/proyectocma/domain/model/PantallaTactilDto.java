package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.EntityReference;

import java.math.BigDecimal;

@EntityReference("PantallaTactil")
public record PantallaTactilDto(
        @DtoId
        Integer id,
        BigDecimal pulgadas,
        String resolucion
) {
}

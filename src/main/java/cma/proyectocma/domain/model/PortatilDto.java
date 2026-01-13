package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;

import java.math.BigDecimal;

public record PortatilDto(
        @DtoId
        Integer id,
        BigDecimal pulgadas
) {
}

package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;

import java.math.BigDecimal;

public record TabletDto(
        @DtoId
        Integer id,
        String sistemaOperativo,
        BigDecimal pulgadas
) {
}

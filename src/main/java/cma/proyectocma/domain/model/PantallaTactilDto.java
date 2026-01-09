package cma.proyectocma.domain.model;

import cma.proyectocma.dao.model.PantallaTactil;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.model.util.DtoId;

import java.math.BigDecimal;

public record PantallaTactilDto(
        @DtoId
        Integer id,
        BigDecimal pulgadas,
        String resolucion
) implements MapperPkSimple<PantallaTactilDto, PantallaTactil> {
}

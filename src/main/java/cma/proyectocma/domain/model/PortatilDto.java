package cma.proyectocma.domain.model;

import cma.proyectocma.dao.model.Portatil;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.model.util.DtoId;

import java.math.BigDecimal;

public record PortatilDto(
        @DtoId
        Integer id,
        BigDecimal pulgadas
) implements MapperPkSimple<PortatilDto, Portatil> {
}

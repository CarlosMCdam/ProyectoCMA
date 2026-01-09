package cma.proyectocma.domain.model;

import cma.proyectocma.dao.model.Tablet;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.model.util.DtoId;

import java.math.BigDecimal;

public record TabletDto(
        @DtoId
        Integer id,
        String sistemaOperativo,
        BigDecimal pulgadas
) implements MapperPkSimple<TabletDto, Tablet> {
}

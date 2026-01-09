package cma.proyectocma.domain.model;

import cma.proyectocma.dao.model.Pc;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.model.util.DtoId;

public record PcDto(
        @DtoId
        Integer id,
        String tipoDisco,
        Integer ramGb
) implements MapperPkSimple<PcDto, Pc> {
}

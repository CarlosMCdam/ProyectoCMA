package cma.proyectocma.domain.model;

import cma.proyectocma.dao.model.Averia;
import cma.proyectocma.domain.model.base.DtoPkSimple;

public record AveriaDto(
        Integer id
) implements DtoPkSimple<AveriaDto, Averia> {
}

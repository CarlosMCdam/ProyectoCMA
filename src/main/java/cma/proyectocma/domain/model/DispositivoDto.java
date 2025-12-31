package cma.proyectocma.domain.model;

import cma.proyectocma.dao.model.Dispositivo;
import cma.proyectocma.domain.model.base.DtoPkSimple;

public record DispositivoDto(
        Integer id
) implements DtoPkSimple<DispositivoDto, Dispositivo> {
}

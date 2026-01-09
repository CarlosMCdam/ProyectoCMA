package cma.proyectocma.domain.model;

import cma.proyectocma.dao.model.Modelo;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.model.util.DtoId;

public record ModeloDto(
        @DtoId
        Integer id,
        String nombre,
        String estado,
        String tipo,
        String descripcion
) {
}

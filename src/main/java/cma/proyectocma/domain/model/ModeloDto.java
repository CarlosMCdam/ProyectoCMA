package cma.proyectocma.domain.model;

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

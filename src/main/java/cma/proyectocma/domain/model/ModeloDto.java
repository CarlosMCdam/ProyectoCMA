package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.EntityReference;

@EntityReference("Modelo")
public record ModeloDto(
        @DtoId
        Integer id,
        String nombre,
        String estado,
        String tipo,
        String descripcion
) {
}

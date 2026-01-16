package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.EntityReference;

@EntityReference("Pc")
public record PcDto(
        @DtoId
        Integer id,
        String tipoDisco,
        Integer ramGb
) {
}

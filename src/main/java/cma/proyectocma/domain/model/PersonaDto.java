package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.EntityReference;

@EntityReference("Persona")
public record PersonaDto(
        @DtoId
        Integer id,
        String gmail
) {
}

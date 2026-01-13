package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;

public record PersonaDto(
        @DtoId
        Integer id,
        String gmail
) {
}

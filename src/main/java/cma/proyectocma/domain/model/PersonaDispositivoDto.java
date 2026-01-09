package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;

public record PersonaDispositivoDto(
        @DtoId(DtoId.IdIndex.ID1)
        Integer idPersona,
        @DtoId(DtoId.IdIndex.ID2)
        Integer idDispositivo
) {
}

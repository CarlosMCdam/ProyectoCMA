package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.EntityReference;
import cma.proyectocma.domain.model.util.IdReference;

@EntityReference("PersonaDispositivo")
public record PersonaDispositivoDto(
        @DtoId(DtoId.IdIndex.ID1)
        @IdReference(IdReference.Entity.PERSONA)
        Integer idPersona,
        @DtoId(DtoId.IdIndex.ID2)
        @IdReference(IdReference.Entity.PERSONA)
        Integer idDispositivo
) {
}

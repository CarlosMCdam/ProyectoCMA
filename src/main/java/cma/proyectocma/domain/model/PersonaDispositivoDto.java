package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.base.DtoPkDoble;

public record PersonaDispositivoDto(
        @DtoPkDoble.Id(DtoPkDoble.Id.IdIndex.ID1)
        Integer idPersona,
        @DtoPkDoble.Id(DtoPkDoble.Id.IdIndex.ID2)
        Integer idDispositivo
) {
}

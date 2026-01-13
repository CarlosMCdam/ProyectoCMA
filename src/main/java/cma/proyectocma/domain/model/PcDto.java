package cma.proyectocma.domain.model;

import cma.proyectocma.domain.model.util.DtoId;

public record PcDto(
        @DtoId
        Integer id,
        String tipoDisco,
        Integer ramGb
) {
}

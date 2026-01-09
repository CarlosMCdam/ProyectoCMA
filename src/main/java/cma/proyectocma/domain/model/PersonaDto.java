package cma.proyectocma.domain.model;

import cma.proyectocma.dao.model.Persona;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.model.util.DtoId;

public record PersonaDto(
        @DtoId
        Integer id,
        String gmail
) implements MapperPkSimple<PersonaDto, Persona> {
}

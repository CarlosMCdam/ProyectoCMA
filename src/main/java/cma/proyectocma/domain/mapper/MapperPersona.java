package cma.proyectocma.domain.mapper;

import cma.proyectocma.dao.model.Persona;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.mapper.util.IdResolver;
import cma.proyectocma.domain.model.PersonaDto;
import org.springframework.stereotype.Component;

@Component
public final class MapperPersona extends MapperPkSimple<PersonaDto, Persona> {
    public MapperPersona(IdResolver resolver) {
        super(PersonaDto.class, Persona.class, resolver);
    }
}

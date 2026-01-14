package cma.proyectocma.domain.mapper;

import cma.proyectocma.dao.model.PersonaDispositivo;
import cma.proyectocma.domain.mapper.base.MapperPkDoble;
import cma.proyectocma.domain.mapper.base.util.IdResolver;
import cma.proyectocma.domain.model.PersonaDispositivoDto;
import org.springframework.stereotype.Component;

@Component
public final class MapperPersonaDispositivo extends MapperPkDoble<PersonaDispositivoDto, PersonaDispositivo> {
    public MapperPersonaDispositivo(IdResolver resolver) {
        super(PersonaDispositivoDto.class, PersonaDispositivo.class, resolver);
    }
}

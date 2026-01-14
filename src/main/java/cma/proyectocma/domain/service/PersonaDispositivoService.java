package cma.proyectocma.domain.service;

import cma.proyectocma.domain.mapper.MapperPersonaDispositivo;
import cma.proyectocma.dao.model.PersonaDispositivo;
import cma.proyectocma.dao.repository.RepositoryPersonaDispositivo;
import cma.proyectocma.domain.model.PersonaDispositivoDto;
import cma.proyectocma.domain.service.base.ServicePkDoble;
import org.springframework.stereotype.Service;

@Service
public final class PersonaDispositivoService extends ServicePkDoble<PersonaDispositivoDto, PersonaDispositivo> {
    public PersonaDispositivoService(RepositoryPersonaDispositivo repository, MapperPersonaDispositivo mapper) {
        super(PersonaDispositivoDto.class, repository, mapper);
    }
}

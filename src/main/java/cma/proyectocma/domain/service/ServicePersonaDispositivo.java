package cma.proyectocma.domain.service;

import cma.proyectocma.dao.RepositoryPkDoble;
import cma.proyectocma.dao.model.PersonaDispositivo;
import cma.proyectocma.domain.model.PersonaDispositivoDto;
import cma.proyectocma.domain.service.base.ServicePkDoble;
import org.springframework.stereotype.Service;

@Service
public final class ServicePersonaDispositivo extends ServicePkDoble<PersonaDispositivoDto, PersonaDispositivo> {
    public ServicePersonaDispositivo(RepositoryPkDoble.RepositoryPersonaDispositivo repository) {
        super(repository, PersonaDispositivoDto.class);
    }
}

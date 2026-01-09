package cma.proyectocma.domain.service;

import cma.proyectocma.dao.RepositoryPkSimple;
import cma.proyectocma.dao.model.Persona;
import cma.proyectocma.domain.model.PersonaDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServicePersona extends ServicePkSimple<PersonaDto, Persona> {
    public ServicePersona(RepositoryPkSimple.RepositoryPersona repository) {
        super(repository, PersonaDto.class);
    }
}

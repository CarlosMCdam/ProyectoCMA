package cma.proyectocma.domain.service;

import cma.proyectocma.domain.mapper.MapperPersona;
import cma.proyectocma.dao.model.Persona;
import cma.proyectocma.dao.repository.RepositoryPersona;
import cma.proyectocma.domain.model.PersonaDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServicePersona extends ServicePkSimple<PersonaDto, Persona> {
    public ServicePersona(RepositoryPersona repository, MapperPersona mapper) {
        super(PersonaDto.class, repository, mapper);
    }
}

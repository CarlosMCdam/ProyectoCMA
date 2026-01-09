package cma.proyectocma.domain.service;

import cma.proyectocma.dao.RepositoryPkSimple;
import cma.proyectocma.dao.model.Averia;
import cma.proyectocma.domain.model.AveriaDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServiceAveria extends ServicePkSimple<AveriaDto, Averia> {
    public ServiceAveria(RepositoryPkSimple.RepositoryAveria repository) {
        super(repository, AveriaDto.class);
    }
}

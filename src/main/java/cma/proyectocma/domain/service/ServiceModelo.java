package cma.proyectocma.domain.service;

import cma.proyectocma.dao.RepositoryPkSimple;
import cma.proyectocma.dao.model.Modelo;
import cma.proyectocma.domain.model.ModeloDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServiceModelo extends ServicePkSimple<ModeloDto, Modelo> {
    public ServiceModelo(RepositoryPkSimple.RepositoryModelo repository) {
        super(repository, ModeloDto.class);
    }
}

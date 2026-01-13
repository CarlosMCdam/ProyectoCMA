package cma.proyectocma.domain.service;

import cma.proyectocma.domain.mapper.MapperModelo;
import cma.proyectocma.dao.model.Modelo;
import cma.proyectocma.dao.repository.RepositoryModelo;
import cma.proyectocma.domain.model.ModeloDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServiceModelo extends ServicePkSimple<ModeloDto, Modelo> {
    public ServiceModelo(RepositoryModelo repository, MapperModelo mapper) {
        super(ModeloDto.class, repository, mapper);
    }
}

package cma.proyectocma.domain.service;

import cma.proyectocma.domain.mapper.MapperAveria;
import cma.proyectocma.dao.model.Averia;
import cma.proyectocma.dao.repository.RepositoryAveria;
import cma.proyectocma.domain.model.AveriaDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServiceAveria extends ServicePkSimple<AveriaDto, Averia> {
    public ServiceAveria(RepositoryAveria repository, MapperAveria mapper) {
        super(AveriaDto.class, repository, mapper);
    }
}

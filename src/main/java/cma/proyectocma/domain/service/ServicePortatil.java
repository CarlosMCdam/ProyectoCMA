package cma.proyectocma.domain.service;

import cma.proyectocma.domain.mapper.MapperPortatil;
import cma.proyectocma.dao.model.Portatil;
import cma.proyectocma.dao.repository.RepositoryPortatil;
import cma.proyectocma.domain.model.PortatilDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServicePortatil extends ServicePkSimple<PortatilDto, Portatil> {
    public ServicePortatil(RepositoryPortatil repository, MapperPortatil mapper) {
        super(PortatilDto.class, repository, mapper);
    }
}

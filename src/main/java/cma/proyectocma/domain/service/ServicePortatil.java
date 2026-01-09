package cma.proyectocma.domain.service;

import cma.proyectocma.dao.RepositoryPkSimple;
import cma.proyectocma.dao.model.Portatil;
import cma.proyectocma.domain.model.PortatilDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServicePortatil extends ServicePkSimple<PortatilDto, Portatil> {
    public ServicePortatil(RepositoryPkSimple.RepositoryPortatil repository) {
        super(repository, PortatilDto.class);
    }
}

package cma.proyectocma.domain.service;

import cma.proyectocma.dao.RepositoryPkSimple;
import cma.proyectocma.dao.model.Pc;
import cma.proyectocma.domain.model.PcDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServicePc extends ServicePkSimple<PcDto, Pc> {
    public ServicePc(RepositoryPkSimple.RepositoryPc repository) {
        super(repository, PcDto.class);
    }
}

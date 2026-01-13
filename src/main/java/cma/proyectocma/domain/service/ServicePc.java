package cma.proyectocma.domain.service;

import cma.proyectocma.domain.mapper.MapperPc;
import cma.proyectocma.dao.model.Pc;
import cma.proyectocma.dao.repository.RepositoryPc;
import cma.proyectocma.domain.model.PcDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServicePc extends ServicePkSimple<PcDto, Pc> {
    public ServicePc(RepositoryPc repository, MapperPc mapper) {
        super(PcDto.class, repository, mapper);
    }
}

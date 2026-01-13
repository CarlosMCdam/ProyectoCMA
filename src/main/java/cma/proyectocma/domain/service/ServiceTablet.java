package cma.proyectocma.domain.service;

import cma.proyectocma.domain.mapper.MapperTablet;
import cma.proyectocma.dao.model.Tablet;
import cma.proyectocma.dao.repository.RepositoryTablet;
import cma.proyectocma.domain.model.TabletDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServiceTablet extends ServicePkSimple<TabletDto, Tablet> {
    public ServiceTablet(RepositoryTablet repository, MapperTablet mapper) {
        super(TabletDto.class, repository, mapper);
    }
}

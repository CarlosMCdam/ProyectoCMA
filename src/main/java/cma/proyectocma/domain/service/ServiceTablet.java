package cma.proyectocma.domain.service;

import cma.proyectocma.dao.RepositoryPkSimple;
import cma.proyectocma.dao.model.Tablet;
import cma.proyectocma.domain.model.TabletDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServiceTablet extends ServicePkSimple<TabletDto, Tablet> {
    public ServiceTablet(RepositoryPkSimple.RepositoryTablet repository) {
        super(repository, TabletDto.class);
    }
}

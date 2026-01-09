package cma.proyectocma.domain.service;

import cma.proyectocma.dao.RepositoryPkSimple;
import cma.proyectocma.dao.model.Dispositivo;
import cma.proyectocma.domain.model.DispositivoDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServiceDispositivo extends ServicePkSimple<DispositivoDto, Dispositivo> {
    public ServiceDispositivo(RepositoryPkSimple.RepositoryDispositivo repository) {
        super(repository, DispositivoDto.class);
    }
}

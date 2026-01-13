package cma.proyectocma.domain.service;

import cma.proyectocma.domain.mapper.MapperDispositivo;
import cma.proyectocma.dao.model.Dispositivo;
import cma.proyectocma.dao.repository.RepositoryDispositivo;
import cma.proyectocma.domain.model.DispositivoDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServiceDispositivo extends ServicePkSimple<DispositivoDto, Dispositivo> {
    public ServiceDispositivo(RepositoryDispositivo repository, MapperDispositivo mapper) {
        super(DispositivoDto.class, repository, mapper);
    }
}

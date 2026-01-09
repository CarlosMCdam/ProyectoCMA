package cma.proyectocma.domain.service;

import cma.proyectocma.dao.RepositoryPkSimple;
import cma.proyectocma.dao.model.Impresora;
import cma.proyectocma.domain.model.ImpresoraDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServiceImpresora extends ServicePkSimple<ImpresoraDto, Impresora> {
    public ServiceImpresora(RepositoryPkSimple.RepositoryImpresora repository) {
        super(repository, ImpresoraDto.class);
    }
}

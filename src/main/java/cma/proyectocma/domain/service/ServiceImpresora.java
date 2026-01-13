package cma.proyectocma.domain.service;

import cma.proyectocma.domain.mapper.MapperImpresora;
import cma.proyectocma.dao.model.Impresora;
import cma.proyectocma.dao.repository.RepositoryImpresora;
import cma.proyectocma.domain.model.ImpresoraDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServiceImpresora extends ServicePkSimple<ImpresoraDto, Impresora> {
    public ServiceImpresora(RepositoryImpresora repository, MapperImpresora mapper) {
        super(ImpresoraDto.class, repository, mapper);
    }
}

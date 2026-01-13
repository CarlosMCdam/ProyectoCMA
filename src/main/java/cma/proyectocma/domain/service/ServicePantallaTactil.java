package cma.proyectocma.domain.service;

import cma.proyectocma.domain.mapper.MapperPantallaTactil;
import cma.proyectocma.dao.model.PantallaTactil;
import cma.proyectocma.dao.repository.RepositoryPantallaTactil;
import cma.proyectocma.domain.model.PantallaTactilDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServicePantallaTactil extends ServicePkSimple<PantallaTactilDto, PantallaTactil> {
    public ServicePantallaTactil(RepositoryPantallaTactil repository, MapperPantallaTactil mapper) {
        super(PantallaTactilDto.class, repository, mapper);
    }
}

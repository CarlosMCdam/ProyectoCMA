package cma.proyectocma.domain.service;

import cma.proyectocma.dao.RepositoryPkSimple;
import cma.proyectocma.dao.model.PantallaTactil;
import cma.proyectocma.domain.model.PantallaTactilDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServicePantallaTactil extends ServicePkSimple<PantallaTactilDto, PantallaTactil> {
    public ServicePantallaTactil(RepositoryPkSimple.RepositoryPantallaTactil repository) {
        super(repository, PantallaTactilDto.class);
    }
}

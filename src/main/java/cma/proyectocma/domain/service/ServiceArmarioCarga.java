package cma.proyectocma.domain.service;

import cma.proyectocma.domain.mapper.MapperArmarioCarga;
import cma.proyectocma.dao.model.ArmarioCarga;
import cma.proyectocma.dao.repository.RepositoryArmarioCarga;
import cma.proyectocma.domain.model.ArmarioCargaDto;
import cma.proyectocma.domain.service.base.ServicePkSimple;
import org.springframework.stereotype.Service;

@Service
public final class ServiceArmarioCarga extends ServicePkSimple<ArmarioCargaDto, ArmarioCarga> {
    public ServiceArmarioCarga(RepositoryArmarioCarga repository, MapperArmarioCarga mapper) {
        super(ArmarioCargaDto.class, repository, mapper);
    }
}

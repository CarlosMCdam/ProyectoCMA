package cma.proyectocma.domain.mapper;

import cma.proyectocma.dao.model.ArmarioCarga;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.mapper.base.util.IdResolver;
import cma.proyectocma.domain.model.ArmarioCargaDto;
import org.springframework.stereotype.Component;

@Component
public final class MapperArmarioCarga extends MapperPkSimple<ArmarioCargaDto, ArmarioCarga> {
    public MapperArmarioCarga(IdResolver resolver) {
        super(ArmarioCargaDto.class, ArmarioCarga.class, resolver);
    }
}

package cma.proyectocma.domain.mapper;

import cma.proyectocma.dao.model.PantallaTactil;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.mapper.util.IdResolver;
import cma.proyectocma.domain.model.PantallaTactilDto;
import org.springframework.stereotype.Component;

@Component
public final class MapperPantallaTactil extends MapperPkSimple<PantallaTactilDto, PantallaTactil> {
    public MapperPantallaTactil(IdResolver resolver) {
        super(PantallaTactilDto.class, PantallaTactil.class, resolver);
    }
}

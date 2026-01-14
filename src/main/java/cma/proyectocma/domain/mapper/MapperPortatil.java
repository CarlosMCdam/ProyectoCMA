package cma.proyectocma.domain.mapper;

import cma.proyectocma.dao.model.Portatil;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.mapper.base.util.IdResolver;
import cma.proyectocma.domain.model.PortatilDto;
import org.springframework.stereotype.Component;

@Component
public final class MapperPortatil extends MapperPkSimple<PortatilDto, Portatil> {
    public MapperPortatil(IdResolver resolver) {
        super(PortatilDto.class, Portatil.class, resolver);
    }
}

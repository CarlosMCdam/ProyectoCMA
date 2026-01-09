package cma.proyectocma.domain.mapper;

import cma.proyectocma.dao.model.Averia;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.mapper.util.IdResolver;
import cma.proyectocma.domain.model.AveriaDto;
import org.springframework.stereotype.Component;

@Component
public final class MapperAveria extends MapperPkSimple<AveriaDto, Averia> {
    public MapperAveria(IdResolver resolver) {
        super(AveriaDto.class, Averia.class, resolver);
    }
}

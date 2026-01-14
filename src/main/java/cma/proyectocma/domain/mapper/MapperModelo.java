package cma.proyectocma.domain.mapper;

import cma.proyectocma.dao.model.Modelo;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.mapper.base.util.IdResolver;
import cma.proyectocma.domain.model.ModeloDto;
import org.springframework.stereotype.Component;

@Component
public final class MapperModelo extends MapperPkSimple<ModeloDto, Modelo> {
    public MapperModelo(IdResolver resolver) {
        super(ModeloDto.class, Modelo.class, resolver);
    }
}

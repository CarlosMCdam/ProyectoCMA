package cma.proyectocma.domain.mapper;

import cma.proyectocma.dao.model.Pc;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.mapper.util.IdResolver;
import cma.proyectocma.domain.model.PcDto;
import org.springframework.stereotype.Component;

@Component
public final class MapperPc extends MapperPkSimple<PcDto, Pc> {
    public MapperPc(IdResolver resolver) {
        super(PcDto.class, Pc.class, resolver);
    }
}

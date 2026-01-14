package cma.proyectocma.domain.mapper;

import cma.proyectocma.dao.model.Dispositivo;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.mapper.base.util.IdResolver;
import cma.proyectocma.domain.model.DispositivoDto;
import org.springframework.stereotype.Component;

@Component
public final class MapperDispositivo extends MapperPkSimple<DispositivoDto, Dispositivo> {
    public MapperDispositivo(IdResolver resolver) {
        super(DispositivoDto.class, Dispositivo.class, resolver);
    }
}

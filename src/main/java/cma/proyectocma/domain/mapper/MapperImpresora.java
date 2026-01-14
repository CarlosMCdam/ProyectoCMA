package cma.proyectocma.domain.mapper;

import cma.proyectocma.dao.model.Impresora;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.mapper.base.util.IdResolver;
import cma.proyectocma.domain.model.ImpresoraDto;
import org.springframework.stereotype.Component;

@Component
public final class MapperImpresora extends MapperPkSimple<ImpresoraDto, Impresora> {
    public MapperImpresora(IdResolver resolver) {
        super(ImpresoraDto.class, Impresora.class, resolver);
    }
}

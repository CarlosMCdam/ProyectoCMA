package cma.proyectocma.domain.mapper;

import cma.proyectocma.dao.model.Tablet;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;
import cma.proyectocma.domain.mapper.util.IdResolver;
import cma.proyectocma.domain.model.TabletDto;
import org.springframework.stereotype.Component;

@Component
public final class MapperTablet extends MapperPkSimple<TabletDto, Tablet> {
    public MapperTablet(IdResolver resolver) {
        super(TabletDto.class, Tablet.class, resolver);
    }
}

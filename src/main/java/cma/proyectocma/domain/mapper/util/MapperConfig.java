package cma.proyectocma.domain.mapper.util;

import cma.proyectocma.dao.model.*;
import cma.proyectocma.domain.mapper.base.PkDobleMapper;
import cma.proyectocma.domain.mapper.base.PkSimpleMapper;
import cma.proyectocma.domain.model.*;
import org.springframework.context.annotation.Configuration;

@MapperProvider(
        mapperProvider = PkSimpleMapper.class,
        entities = {
                ArmarioCarga.class,
                Averia.class,
                Dispositivo.class,
                Impresora.class,
                Modelo.class,
                PantallaTactil.class,
                Pc.class,
                Persona.class,
                Portatil.class,
                Tablet.class
        },
        dtos = {
                ArmarioCargaDto.class,
                AveriaDto.class,
                DispositivoDto.class,
                ImpresoraDto.class,
                ModeloDto.class,
                PantallaTactilDto.class,
                PcDto.class,
                PersonaDto.class,
                PortatilDto.class,
                TabletDto.class
        }
)
@MapperProvider(
        mapperProvider = PkDobleMapper.class,
        entities = {
                PersonaDispositivo.class
        },
        dtos = {
                PersonaDispositivoDto.class
        }
)
@Configuration(proxyBeanMethods = false)
public final class MapperConfig {
}

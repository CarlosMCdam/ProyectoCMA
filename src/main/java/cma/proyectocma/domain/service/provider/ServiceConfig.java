package cma.proyectocma.domain.service.provider;

import cma.proyectocma.dao.model.*;
import cma.proyectocma.domain.service.base.PkDobleService;
import cma.proyectocma.domain.service.base.PkSimpleService;
import org.springframework.context.annotation.Configuration;

@EnableServiceProvider(
        serviceProvider = PkSimpleService.class,
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
        }
)
@EnableServiceProvider(
        serviceProvider = PkDobleService.class,
        entities = {
                PersonaDispositivo.class
        }
)
@Configuration(proxyBeanMethods = false)
public final class ServiceConfig {
}

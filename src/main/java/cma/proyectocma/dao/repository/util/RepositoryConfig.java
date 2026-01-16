package cma.proyectocma.dao.repository.util;

import cma.proyectocma.dao.model.*;
import cma.proyectocma.dao.repository.base.PkDobleRepository;
import cma.proyectocma.dao.repository.base.PkSimpleRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@RepositoryProvider(
        repositoryProvider = PkSimpleRepository.class,
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
@RepositoryProvider(
        repositoryProvider = PkDobleRepository.class,
        entities = {
                PersonaDispositivo.class
        }
)
@EnableJpaRepositories(basePackages = "cma.proyectocma.dao.repository")
@Configuration(proxyBeanMethods = false)
public final class RepositoryConfig {
}

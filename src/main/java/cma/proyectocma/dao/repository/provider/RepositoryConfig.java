package cma.proyectocma.dao.repository.provider;

import cma.proyectocma.dao.model.*;
import cma.proyectocma.dao.repository.base.PkDobleRepository;
import cma.proyectocma.dao.repository.base.PkSimpleRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableRepositoryProvider(
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
@EnableRepositoryProvider(
        repositoryProvider = PkDobleRepository.class,
        entities = {
                PersonaDispositivo.class
        }
)
@EnableJpaRepositories(basePackages = "cma.proyectocma.dao.repository")
@Configuration(proxyBeanMethods = false)
public final class RepositoryConfig {
}

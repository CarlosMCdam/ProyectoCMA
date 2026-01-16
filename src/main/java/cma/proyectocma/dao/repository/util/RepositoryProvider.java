package cma.proyectocma.dao.repository.util;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RepositoryRegistrar.class)
@Repeatable(RepositoryProviderContainer.class)
public @interface RepositoryProvider {

    Class<?> repositoryProvider();
    Class<?>[] entities();

}

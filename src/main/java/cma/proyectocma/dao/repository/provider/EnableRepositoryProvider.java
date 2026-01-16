package cma.proyectocma.dao.repository.provider;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RepositoryRegistrar.class)
@Repeatable(EnableRepositoryProviderContainer.class)
public @interface EnableRepositoryProvider {

    Class<?> repositoryProvider();
    Class<?>[] entities();

}

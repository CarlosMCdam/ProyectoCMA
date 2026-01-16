package cma.proyectocma.domain.mapper.util;

import cma.proyectocma.domain.mapper.base.util.IdResolver;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MapperRegistrar.class)
@Repeatable(MapperProviderContainer.class)
public @interface MapperProvider {

    Class<?> mapperProvider();
    Class<? extends Record>[] dtos();
    Class<?>[] entities();

}

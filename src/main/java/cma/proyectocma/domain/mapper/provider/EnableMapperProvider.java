package cma.proyectocma.domain.mapper.provider;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(EnableMapperProviderContainer.class)
public @interface EnableMapperProvider {

    Class<?> mapperProvider();

    Class<? extends Record>[] dtos();

    Class<?>[] entities();

}

package cma.proyectocma.domain.mapper.provider;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(MapperRegistrar.class)
public @interface EnableMapperProviderContainer {

    EnableMapperProvider[] value();

}

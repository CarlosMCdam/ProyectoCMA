package cma.proyectocma.domain.service.util;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ServiceRegistrar.class)
@Repeatable(ServiceProviderContainer.class)
public @interface ServiceProvider {

    Class<?> serviceProvider();

    Class<?>[] entities();

}

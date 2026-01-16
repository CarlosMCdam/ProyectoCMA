package cma.proyectocma.domain.service.provider;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ServiceRegistrar.class)
@Repeatable(EnableServiceProviderContainer.class)
public @interface EnableServiceProvider {

    Class<?> serviceProvider();

    Class<?>[] entities();

}

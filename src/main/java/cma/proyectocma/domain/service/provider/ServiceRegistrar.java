package cma.proyectocma.domain.service.provider;

import cma.proyectocma.domain.mapper.base.util.IdResolver;
import cma.proyectocma.domain.service.exception.ServiceRegistrarException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.MethodCall;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public final class ServiceRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Arrays.stream((AnnotationAttributes[]) importingClassMetadata.getAnnotationAttributes(
                EnableServiceProviderContainer.class.getName()
        ).get("value")).forEach(annotationAttributes -> {
            Arrays.stream(((Class<?>[]) annotationAttributes.get("entities"))).toList().forEach(entityClass -> {
                Class<?> serviceBaseClass = (Class<?>) annotationAttributes.get("serviceProvider");
                try {
                    RootBeanDefinition beanDefinition = new RootBeanDefinition(new ByteBuddy()
                            .subclass(serviceBaseClass, ConstructorStrategy.Default.NO_CONSTRUCTORS)
                            .name(entityClass.getSimpleName() + "Service")
                            .defineConstructor(Visibility.PUBLIC)
                            .withParameters(Class.class, Class.class, IdResolver.class)
                            .intercept(MethodCall.invoke(
                                    serviceBaseClass.getDeclaredConstructor(Class.class, Class.class, IdResolver.class)
                            ).withAllArguments())
                            .make()
                            .load(getClass().getClassLoader())
                            .getLoaded());
                    ConstructorArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();
                    constructorArgumentValues.addGenericArgumentValue(
                            new RuntimeBeanReference(entityClass.getSimpleName() + "Repository")
                    );
                    constructorArgumentValues.addGenericArgumentValue(
                            new RuntimeBeanReference(entityClass.getSimpleName() + "Mapper")
                    );
                    registry.registerBeanDefinition(
                            StringUtils.uncapitalize(entityClass.getSimpleName()) +
                                    "Service", beanDefinition
                    );
                } catch (NoSuchMethodException e) {
                    throw new ServiceRegistrarException(e);
                }
            });
        });
    }

}

package cma.proyectocma.domain.mapper.provider;

import cma.proyectocma.domain.mapper.base.util.IdResolver;
import cma.proyectocma.domain.mapper.exception.MapperRegistrarException;
import cma.proyectocma.domain.model.util.EntityReference;
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

public final class MapperRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Arrays.stream((AnnotationAttributes[]) importingClassMetadata.getAnnotationAttributes(
                EnableMapperProviderContainer.class.getName()
        ).get("value")).forEach(annotationAttributes -> {
            Arrays.stream(((Class<?>[]) annotationAttributes.get("entities"))).toList().forEach(entityClass -> {
                Class<?> mapperBaseClass = (Class<?>) annotationAttributes.get("mapperProvider");
                try {
                    RootBeanDefinition beanDefinition = new RootBeanDefinition(new ByteBuddy()
                            .subclass(mapperBaseClass, ConstructorStrategy.Default.NO_CONSTRUCTORS)
                            .name(entityClass.getSimpleName() + "Mapper")
                            .defineConstructor(Visibility.PUBLIC)
                            .withParameters(Class.class, Class.class, IdResolver.class)
                            .intercept(MethodCall.invoke(
                                    mapperBaseClass.getDeclaredConstructor(Class.class, Class.class, IdResolver.class)
                            ).withAllArguments())
                            .make()
                            .load(getClass().getClassLoader())
                            .getLoaded());
                    ConstructorArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();
                    constructorArgumentValues.addGenericArgumentValue(Arrays.stream(
                            ((Class<? extends Record>[]) annotationAttributes.get("dtos"))
                    ).filter(dtoClassTemp -> {
                        EntityReference entityReference = dtoClassTemp.getAnnotation(EntityReference.class);
                        return entityReference != null && entityReference.value().equals(entityClass.getSimpleName());
                    }).findAny().orElseThrow(() -> new MapperRegistrarException(
                            "No DTO found for entity: " + entityClass.getSimpleName()
                    )));
                    constructorArgumentValues.addGenericArgumentValue(entityClass);
                    constructorArgumentValues.addGenericArgumentValue(new RuntimeBeanReference("idResolver"));
                    registry.registerBeanDefinition(
                            StringUtils.uncapitalize(entityClass.getSimpleName()) + "Mapper",
                            beanDefinition
                    );
                } catch (NoSuchMethodException e) {
                    throw new MapperRegistrarException(e);
                }
            });
        });
    }

}

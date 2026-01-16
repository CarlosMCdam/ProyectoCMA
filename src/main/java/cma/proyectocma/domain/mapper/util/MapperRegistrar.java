package cma.proyectocma.domain.mapper.util;

import cma.proyectocma.domain.model.util.EntityReference;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;

public final class MapperRegistrar implements ImportBeanDefinitionRegistrar {

    public MapperRegistrar() {
        System.out.println("osdikjnfgoisdgfdAAAAAAAAAAAAAAAAAAAA");
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        System.out.println("sdfkjsdnlkifdgfdAAAAAAAAAAAAAAAAAAAA");
        Arrays.stream((AnnotationAttributes[]) importingClassMetadata.getAnnotationAttributes(
                MapperProviderContainer.class.getName()
        ).get("value")).forEach(annotationAttributes -> {
            Arrays.stream(((Class<?>[]) annotationAttributes.get("entities"))).toList().forEach(entityClass -> {
                RootBeanDefinition beanDefinition = new RootBeanDefinition((Class<?>) annotationAttributes.get("mapperProvider"));
                ConstructorArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();
                constructorArgumentValues.addGenericArgumentValue(Arrays.stream(
                        ((Class<? extends Record>[]) annotationAttributes.get("dtos"))
                ).filter(dto -> {
                    EntityReference entityReference = dto.getAnnotation(EntityReference.class);
                    return entityReference != null && entityReference.value().equals(entityClass.getSimpleName());
                }).findAny().orElseThrow());
                constructorArgumentValues.addGenericArgumentValue(entityClass);
                constructorArgumentValues.addGenericArgumentValue(
                        new RuntimeBeanReference("IdResolver")
                );
                registry.registerBeanDefinition(entityClass.getSimpleName() + "Mapper", beanDefinition);
            });
        });
    }

}

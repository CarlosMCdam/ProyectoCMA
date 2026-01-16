package cma.proyectocma.domain.service.util;

import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;

public final class ServiceRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Arrays.stream((AnnotationAttributes[]) importingClassMetadata.getAnnotationAttributes(
                ServiceProviderContainer.class.getName()
        ).get("value")).forEach(annotationAttributes -> {
            Arrays.stream(((Class<?>[]) annotationAttributes.get("entities"))).toList().forEach(entityClass -> {
                RootBeanDefinition beanDefinition = new RootBeanDefinition((Class<?>) annotationAttributes.get("serviceProvider"));
                ConstructorArgumentValues constructorArgumentValues = beanDefinition.getConstructorArgumentValues();
                constructorArgumentValues.addGenericArgumentValue(
                        new RuntimeBeanReference(entityClass.getSimpleName() + "Repository")
                );
                constructorArgumentValues.addGenericArgumentValue(
                        new RuntimeBeanReference(entityClass.getSimpleName() + "Mapper")
                );
                registry.registerBeanDefinition(entityClass.getSimpleName() + "Service", beanDefinition);
            });
        });
    }

}

package cma.proyectocma.dao.repository.provider;

import net.bytebuddy.ByteBuddy;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public final class RepositoryRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Arrays.stream((AnnotationAttributes[]) importingClassMetadata.getAnnotationAttributes(
                EnableRepositoryProviderContainer.class.getName()
        ).get("value")).forEach(annotationAttributes -> {
            Arrays.stream(((Class<?>[]) annotationAttributes.get("entities"))).toList().forEach(entityClass -> {
                Class<?> repository = new ByteBuddy()
                        .makeInterface()
                        .name("cma.proyectocma.dao.repository." + entityClass.getSimpleName() + "Repository")
                        .implement((Class<?>) annotationAttributes.get("repositoryProvider"))
                        .make()
                        .load(getClass().getClassLoader())
                        .getLoaded();
                AnnotatedGenericBeanDefinition beanDefinition = new AnnotatedGenericBeanDefinition(repository);
                beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
                registry.registerBeanDefinition(
                        StringUtils.uncapitalize(entityClass.getSimpleName()) + "Repository",
                        beanDefinition
                );
                RootBeanDefinition proxyBeanDefinition = new RootBeanDefinition(JpaRepositoryFactoryBean.class);
                proxyBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(repository);
                proxyBeanDefinition.getPropertyValues().add(
                        "entityManager",
                        new RuntimeBeanReference("entityManagerFactory")
                );
                registry.registerBeanDefinition(
                        StringUtils.uncapitalize(entityClass.getSimpleName()) + "RepositoryProxy",
                        proxyBeanDefinition
                );
            });
        });
    }

}

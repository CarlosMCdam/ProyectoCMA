package cma.proyectocma.domain.model.base;

import cma.proyectocma.dao.model.base.EntityPkDoble;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MapsId;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface DtoPkDoble<T extends DtoPkDoble, E extends EntityPkDoble> {
    default T fromEntity(E entity, Class<T> daoClass) {
        try {
            T dao = daoClass.getDeclaredConstructor().newInstance();
            Field id = Arrays.stream(entity.getClass().getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(EmbeddedId.class))
                    .findFirst()
                    .orElseThrow();
            id.setAccessible(true);
            EntityPkDoble.PkDoble pk = (EntityPkDoble.PkDoble) id.get(entity);
            getAttributes(entity.getClass(), entityField -> {
                if (entityField.isAnnotationPresent(EmbeddedId.class)) getAttributes(daoClass, daoField -> {
                    Id idAnnotation = daoField.getAnnotation(Id.class);
                    if (idAnnotation != null) getAttributes(entity.getClass(), entityPkField -> {
                        MapsId mapsIdAnnotation = entityPkField.getAnnotation(MapsId.class);
                        if (mapsIdAnnotation != null) {
                            String idName = idAnnotation.value().idName;
                            if (mapsIdAnnotation.value().equals(idName))
                                setAttribute(dao, daoField.getName(), pk, idName);
                        }
                    }).count();
                }).count();
                else setAttribute(dao, entityField.getName(), entity, entityField);
            }).count();
            return dao;
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    default E toEntity(T dao, Class<E> entityClass) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            EntityPkDoble.PkDoble pk = EntityPkDoble.PkDoble.class.getDeclaredConstructor().newInstance();
            getAttributes(dao.getClass(), daoField -> {
                Id idAnnotation = daoField.getAnnotation(Id.class);
                if (idAnnotation != null) setAttribute(pk, idAnnotation.value().idName, dao, daoField);
            }).count();
            entity.setId(pk);
            getAttributes(dao.getClass(), daoField -> {
                setAttribute(entity, daoField.getName(), dao, daoField);
            }).count();
            return entity;
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<Field> getAttributes(Class<?> objectClass, Consumer<Field> consumer) {
        return Arrays.stream(objectClass.getDeclaredFields()).peek(field -> {
            field.setAccessible(true);
            consumer.accept(field);
        });
    }

    private void setAttribute(Object target, String targetFieldName, Object origin, String originFieldname) {
        try {
            Field targetField = target.getClass().getDeclaredField(targetFieldName);
            targetField.setAccessible(true);
            Field originField = origin.getClass().getDeclaredField(originFieldname);
            originField.setAccessible(true);
            targetField.set(target, originField.get(origin));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void setAttribute(Object target, String targetFieldName, Object origin, Field originField) {
        try {
            Field targetField = target.getClass().getDeclaredField(targetFieldName);
            targetField.setAccessible(true);
            originField.setAccessible(true);
            targetField.set(target, originField.get(origin));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Id {

        IdIndex value();

        enum IdIndex {
            ID1("id1"), ID2("id2");

            private final String idName;

            IdIndex(String idName) {
                this.idName = idName;
            }
        }
    }
}

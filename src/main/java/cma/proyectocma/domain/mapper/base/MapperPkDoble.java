package cma.proyectocma.domain.mapper.base;

import cma.proyectocma.domain.mapper.util.IdResolver;
import cma.proyectocma.dao.model.base.EntityPkDoble;
import cma.proyectocma.domain.model.util.DtoId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MapsId;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class MapperPkDoble<T extends Record, E extends EntityPkDoble> {

    Class<T> dtoClass;
    Class<E> entityClass;
    IdResolver resolver;

    public MapperPkDoble(Class<T> dtoClass, Class<E> entityClass, IdResolver resolver) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
        this.resolver = resolver;
    }

    public T fromEntity(E entity, Class<T> dtoClass) {
        try {
            T dao = dtoClass.getDeclaredConstructor().newInstance();
            Field id = Arrays.stream(entity.getClass().getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(EmbeddedId.class))
                    .findFirst()
                    .orElseThrow();
            id.setAccessible(true);
            EntityPkDoble.PkDoble pk = (EntityPkDoble.PkDoble) id.get(entity);
            getAttributes(entity.getClass(), entityField -> {
                if (entityField.isAnnotationPresent(EmbeddedId.class)) getAttributes(dtoClass, dtoField -> {
                    DtoId dtoIdAnnotation = dtoField.getAnnotation(DtoId.class);
                    if (dtoIdAnnotation != null) getAttributes(entity.getClass(), entityPkField -> {
                        MapsId mapsIdAnnotation = entityPkField.getAnnotation(MapsId.class);
                        if (mapsIdAnnotation != null) {
                            String idName = dtoIdAnnotation.value().getIdName();
                            if (mapsIdAnnotation.value().equals(idName))
                                setAttribute(dao, dtoField.getName(), pk, idName);
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

    public E toEntity(T dto, Class<E> entityClass) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            EntityPkDoble.PkDoble pk = EntityPkDoble.PkDoble.class.getDeclaredConstructor().newInstance();
            getAttributes(dto.getClass(), daoField -> {
                DtoId dtoIdAnnotation = daoField.getAnnotation(DtoId.class);
                if (dtoIdAnnotation != null) setAttribute(pk, dtoIdAnnotation.value().getIdName(), dto, daoField);
            }).count();
            entity.setId(pk);
            getAttributes(dto.getClass(), daoField -> {
                if (!daoField.isAnnotationPresent(DtoId.class)) setAttribute(entity, daoField.getName(), dto, daoField);
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

    private void setAttribute(Object target, Object targetReference, Object origin, Object originReference) {
        try {
            Field targetField = targetReference instanceof String fieldName
                    ? target.getClass().getDeclaredField(fieldName)
                    : (Field) targetReference;
            targetField.setAccessible(true);
            Field originField = originReference instanceof String fieldName
                    ? origin.getClass().getDeclaredField(fieldName)
                    : (Field) originReference;
            originField.setAccessible(true);
            targetField.set(target, originField.get(origin));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

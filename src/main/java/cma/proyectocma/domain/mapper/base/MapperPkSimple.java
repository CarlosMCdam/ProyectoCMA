package cma.proyectocma.domain.mapper.base;

import cma.proyectocma.dao.model.base.EntityPkSimple;
import cma.proyectocma.domain.common.C;
import cma.proyectocma.domain.mapper.util.IdResolver;
import cma.proyectocma.domain.model.util.IdReference;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class MapperPkSimple<T extends Record, E extends EntityPkSimple> {

    Class<T> dtoClass;
    Class<E> entityClass;
    IdResolver resolver;

    public MapperPkSimple(Class<T> dtoClass, Class<E> entityClass, IdResolver resolver) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
        this.resolver = resolver;
    }

    public T fromEntity(E entity, Class<T> dtoClass) {
        try {
            T dto = dtoClass.getDeclaredConstructor().newInstance();
            getAttributes(entity.getClass(), entityField -> {
                if (EntityPkSimple.class.isAssignableFrom(entityField.getType()))
                    getAttributes(dtoClass, dtoField -> {
                        IdReference idReference = dtoField.getAnnotation(IdReference.class);
                        if (idReference != null && idReference.value().equals(entityField.getName()))
                            setAttribute(dto, dtoField, getAttributeValue(entityField, entity), C.ENTITY_SIMPLE_FIELD_ID);
                    }).count();
                else setAttribute(dto, entityField, entity, entityField.getName());
            }).count();
            return dto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends MapperPkSimple> E toEntity(T dto, Class<E> entityClass) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            getAttributes(dto.getClass(), field -> {
                if (field.isAnnotationPresent(IdReference.class)) {

                }
                else setAttribute(entity, field, dto, field.getName());
            }).count();
            return entity;
        } catch (Exception e) {
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
            targetField.set(target, getAttributeValue(originField, origin));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object getAttributeValue(Field field, Object object) {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

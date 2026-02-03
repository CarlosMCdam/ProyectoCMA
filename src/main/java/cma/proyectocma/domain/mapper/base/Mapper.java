package cma.proyectocma.domain.mapper.base;

import cma.proyectocma.data.model.base.Entity;
import cma.proyectocma.domain.common.C;
import cma.proyectocma.domain.mapper.PkDobleMapper;
import cma.proyectocma.domain.mapper.PkSimpleMapper;
import cma.proyectocma.domain.mapper.exception.base.MapperException;
import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.IdReference;
import jakarta.persistence.MapsId;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@SuppressWarnings("java:S119")

public abstract sealed class Mapper<D extends Record, E extends Entity> permits PkSimpleMapper, PkDobleMapper {

    public abstract D fromEntity(E entity);

    protected Object[] valuesFromEntity(Class<D> dtoClass, E entity, Class<E> entityClass) {
        return mapDtoComponents(dtoClass, component -> {
            IdReference idReference = component.getAnnotation(IdReference.class);
            if (component.isAnnotationPresent(DtoId.class) && !isSuperclassIdFieldMapped(entityClass))
                return getValue(entity, C.ID).orElseThrow();
            if (idReference != null)
                return getValue(getReferencedEntity(entity, idReference.value().getEntityName()), C.ID).orElseThrow();
            return getValue(entity, component.getName()).orElseThrow();
        }).toArray();
    }

    public abstract E toEntity(D dto);

    protected Optional<Object> getValue(Object origin, Field field) {
        Class<?> targetClass = origin.getClass();
        try {
            final Method getter;
            if (field.getType() == Boolean.class)
                getter = targetClass.getMethod("is" + capitalize(field.getName()));
            else getter = targetClass.getMethod("get" + capitalize(field.getName()));
            return Optional.ofNullable(getter.invoke(origin));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException e) {
            throw new MapperException(e);
        }
    }

    protected Optional<Object> getValue(Object origin, String fieldName) {
        Class<?> targetClass = origin.getClass();
        try {
            return Optional.ofNullable(targetClass.getMethod("get" + capitalize(fieldName)).invoke(origin));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException e) {
            throw new MapperException(e);
        }
    }

    protected void setValue(Object target, Field field, Object value) {
        Class<?> targetClass = target.getClass();
        try {
            targetClass.getMethod("set" + capitalize(field.getName()), field.getType()).invoke(target, value);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException e) {
            throw new MapperException(e);
        }
    }

    protected void setValue(Object target, String fieldName, Object value) {
        Class<?> targetClass = target.getClass();
        try {
            Arrays.stream(targetClass.getMethods())
                    .filter(method -> method.getName().equals("set" + capitalize(fieldName)) && method.getParameterCount() == 1)
                    .findFirst()
                    .orElseThrow(() -> new MapperException(""))
                    .invoke(target, value);
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException e) {
            throw new MapperException(e);
        }
    }

    protected Object getReferencedEntity(E origin, String referencedEntityName) {
        try {
            return Class.forName("cma.proyectocma.data.model." + capitalize(referencedEntityName)).cast(getValue(origin, referencedEntityName).orElseThrow());
        } catch (ClassNotFoundException e) {
            throw new MapperException(e);
        }
    }

    protected boolean isSuperclassIdFieldMapped(Class<E> entityClass) {
        return Arrays.stream(entityClass.getDeclaredFields())
                .anyMatch(field -> field.isAnnotationPresent(MapsId.class));
    }

    protected <R> Stream<R> mapDtoComponents(Class<D> dtoClass, Function<RecordComponent, R> mapper) {
        return Arrays.stream(dtoClass.getRecordComponents()).map(mapper);
    }

    protected Constructor<D> getDtoConstructor(Class<D> dtoClass) {
        try {
            return dtoClass.getDeclaredConstructor(mapDtoComponents(dtoClass, RecordComponent::getType).toArray(Class<?>[]::new));
        } catch (NoSuchMethodException _) {
            return null;
        }
    }

    private String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

}

package cma.proyectocma.domain.mapper.base;

import cma.proyectocma.domain.mapper.exception.FromEntitySimpleException;
import cma.proyectocma.domain.mapper.exception.ToEntitySimpleException;
import cma.proyectocma.domain.mapper.util.IdResolver;
import cma.proyectocma.dao.model.base.EntityPkSimple;
import cma.proyectocma.domain.model.util.IdReference;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

public abstract class MapperPkSimple<T extends Record, E extends EntityPkSimple> {

    Class<T> dtoClass;
    Class<E> entityClass;
    IdResolver resolver;

    public MapperPkSimple(Class<T> dtoClass, Class<E> entityClass, IdResolver resolver) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
        this.resolver = resolver;
    }

    public T fromEntity(@NotNull E entity) {
        try {
            RecordComponent[] components = dtoClass.getRecordComponents();
            return dtoClass.getDeclaredConstructor(
                    Arrays.stream(components).map(RecordComponent::getType).toArray(Class[]::new)
            ).newInstance(Arrays.stream(components).map(component -> {
                IdReference idReference = component.getAnnotation(IdReference.class);
                try {
                    if (idReference != null) {
                        EntityPkSimple referencedEntity = (EntityPkSimple) getFieldValue(entity, idReference.value());
                        return referencedEntity != null ? referencedEntity.getId() : null;
                    } else return getFieldValue(entity, component.getName());
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new FromEntitySimpleException(e);
                }
            }).toArray());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new FromEntitySimpleException(e);
        }
    }

    public E toEntity(@NotNull T dto) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            Arrays.stream(dto.getClass().getRecordComponents()).forEach(component -> {
                IdReference idReference = component.getAnnotation(IdReference.class);
                try {
                    if (idReference != null) {
                        Field entityField = getField(entityClass, component.getName());
                        setField(entity, entityField, resolver.resolveSimple(
                                entityField.getType(), (Integer) getFieldValue(dto, component.getName())
                        ));
                    } else
                        setField(entity, component.getName(), getFieldValue(dto, getField(dtoClass, component.getName())));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new ToEntitySimpleException(e);
                }
            });
            return entity;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new ToEntitySimpleException(e);
        }
    }

    private Field getField(Class<?> objectClass, Object objectReference) throws NoSuchFieldException {
        Field field = null;
        if (objectReference instanceof Field objectField) field = objectField;
        if (objectReference instanceof String objectFieldName) field = objectClass.getDeclaredField(objectFieldName);
        if (field != null) field.setAccessible(true);
        return field;
    }

    private Object getFieldValue(Object object, Object objectReference) throws NoSuchFieldException, IllegalAccessException {
        return getField(object.getClass(), objectReference).get(object);
    }

    private void setField(Object target, Object targetReference, Object value) throws NoSuchFieldException, IllegalAccessException {
        getField(target.getClass(), targetReference).set(target, value);
    }
}

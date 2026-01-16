package cma.proyectocma.domain.mapper.base;

import cma.proyectocma.dao.model.base.PkSimpleEntity;
import cma.proyectocma.domain.mapper.exception.FromEntityPkSimpleException;
import cma.proyectocma.domain.mapper.exception.ToEntityPkSimpleException;
import cma.proyectocma.domain.mapper.base.util.IdResolver;
import cma.proyectocma.domain.model.util.IdReference;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

public abstract non-sealed class PkSimpleMapper<T extends Record, E extends PkSimpleEntity> extends Mapper {

    Class<T> dtoClass;
    @Getter
    Class<E> entityClass;
    IdResolver resolver;

    protected PkSimpleMapper(Class<T> dtoClass, Class<E> entityClass, IdResolver resolver) {
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
                        PkSimpleEntity referencedEntity =
                                (PkSimpleEntity) getFieldValue(entity, idReference.value().getEntityName());
                        return referencedEntity != null ? referencedEntity.getId() : null;
                    } else return getFieldValue(entity, component.getName());
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new FromEntityPkSimpleException(e);
                }
            }).toArray());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new FromEntityPkSimpleException(e);
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
                        setField(entity, component.getName(),
                                getFieldValue(dto, getField(dtoClass, component.getName())));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new ToEntityPkSimpleException(e);
                }
            });
            return entity;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new ToEntityPkSimpleException(e);
        }
    }

}

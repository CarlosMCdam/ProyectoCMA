package cma.proyectocma.domain.mapper.base;

import cma.proyectocma.dao.model.base.PkDobleEntity;
import cma.proyectocma.dao.model.base.PkSimpleEntity;
import cma.proyectocma.domain.common.C;
import cma.proyectocma.domain.mapper.base.util.IdResolver;
import cma.proyectocma.domain.mapper.exception.FromEntityPkDobleException;
import cma.proyectocma.domain.mapper.exception.ToEntityPkDobleException;
import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.IdReference;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MapsId;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

public abstract non-sealed class PkDobleMapper<T extends Record, E extends PkDobleEntity> extends Mapper {

    Class<T> dtoClass;
    Class<E> entityClass;
    IdResolver resolver;

    protected PkDobleMapper(Class<T> dtoClass, Class<E> entityClass, IdResolver resolver) {
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
                DtoId dtoId = component.getAnnotation(DtoId.class);
                IdReference idReference = component.getAnnotation(IdReference.class);
                try {
                    if (dtoId != null) {
                        if (!getField(entityClass.getSuperclass(), C.ID).isAnnotationPresent(EmbeddedId.class))
                            throw new IllegalStateException(C.EXCEPTIONMESSAGE_MAPPER_MISSINGEMBEDDEDANNOTATION);
                        return ((PkSimpleEntity) getFieldValue(entity, Arrays.stream(entityClass.getDeclaredFields())
                                .filter(entityField -> {
                                    MapsId mapsId = entityField.getAnnotation(MapsId.class);
                                    return mapsId != null && mapsId.value().equals(dtoId.value().getIdName());
                                })
                                .findFirst()
                                .orElseThrow()
                        )).getId();
                    }
                    if (idReference != null) {
                        PkSimpleEntity referencedEntity =
                                (PkSimpleEntity) getFieldValue(entity, idReference.value().getEntityName());
                        if (referencedEntity == null)
                            throw new IllegalStateException(C.EXCEPTIONMESSAGE_MAPPER_NULLREFERENCEDENTITY);
                        return referencedEntity.getId();
                    }
                    return getFieldValue(entity, component.getName());
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new FromEntityPkDobleException(e);
                }
            }).toArray());
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new FromEntityPkDobleException(e);
        }
    }

    public E toEntity(@NotNull T dto) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            PkDobleEntity.PkDoble pk = PkDobleEntity.PkDoble.class.getDeclaredConstructor().newInstance();
            Arrays.stream(dto.getClass().getRecordComponents()).forEach(component -> {
                DtoId dtoIdAnnotation = component.getAnnotation(DtoId.class);
                if (dtoIdAnnotation == null) return;
                try {
                    setField(pk, dtoIdAnnotation.value().getIdName(), getFieldValue(dto, component.getName()));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new ToEntityPkDobleException(e);
                }
            });
            entity.setId(pk);
            Arrays.stream(dto.getClass().getRecordComponents()).forEach(component -> {
                if (component.isAnnotationPresent(DtoId.class)) return;
                IdReference idReference = component.getAnnotation(IdReference.class);
                try {
                    if (idReference != null) {
                        Field entityField = getField(entityClass, component.getName());
                        setField(entity, entityField, resolver.resolveSimple(
                                entityField.getType(), (Integer) getFieldValue(dto, component.getName())
                        ));
                    } else setField(entity, component.getName(),
                            getFieldValue(dto, getField(dtoClass, component.getName())));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new ToEntityPkDobleException(e);
                }
            });
            return entity;
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new ToEntityPkDobleException(e);
        }
    }

}

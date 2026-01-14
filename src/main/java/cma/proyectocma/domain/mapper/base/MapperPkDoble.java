package cma.proyectocma.domain.mapper.base;

import cma.proyectocma.dao.model.base.EntityPkDoble;
import cma.proyectocma.dao.model.base.EntityPkSimple;
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

public abstract class MapperPkDoble<T extends Record, E extends EntityPkDoble> {

    Class<T> dtoClass;
    Class<E> entityClass;
    IdResolver resolver;

    public MapperPkDoble(Class<T> dtoClass, Class<E> entityClass, IdResolver resolver) {
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
                            throw new RuntimeException("");
                        return ((EntityPkSimple) getFieldValue(entity, Arrays.stream(entityClass.getDeclaredFields())
                                .filter(entityField -> {
                                    MapsId mapsId = entityField.getAnnotation(MapsId.class);
                                    return mapsId != null && mapsId.value().equals(dtoId.value().getIdName());
                                })
                                .findFirst()
                                .orElseThrow()
                        )).getId();
                    }
                    if (idReference != null) {
                        EntityPkSimple referencedEntity =
                                (EntityPkSimple) getFieldValue(entity, idReference.value().getEntityName());
                        if (referencedEntity == null) throw new RuntimeException("");
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
            EntityPkDoble.PkDoble pk = EntityPkDoble.PkDoble.class.getDeclaredConstructor().newInstance();
            Arrays.stream(dto.getClass().getRecordComponents()).forEach(component -> {
                DtoId dtoIdAnnotation = component.getAnnotation(DtoId.class);
                if (dtoIdAnnotation == null) return;
                try {
                    setField(pk, dtoIdAnnotation.value().getIdName(), getFieldValue(dto, component.getName()));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
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

    private Field getField(Class<?> objectClass, Object objectReference) throws NoSuchFieldException {
        Field field = null;
        if (objectReference instanceof Field objectField) field = objectField;
        if (objectReference instanceof String objectFieldName)
            field = objectClass.getDeclaredField(objectFieldName);
        if (field != null) field.setAccessible(true);
        return field;
    }

    private Object getFieldValue(@NotNull Object object, Object objectReference)
            throws NoSuchFieldException, IllegalAccessException {
        return getField(object.getClass(), objectReference).get(object);
    }

    private void setField(@NotNull Object target, Object targetReference, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        getField(target.getClass(), targetReference).set(target, value);
    }
}

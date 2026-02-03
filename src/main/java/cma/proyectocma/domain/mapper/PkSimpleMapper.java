package cma.proyectocma.domain.mapper;

import cma.proyectocma.data.model.base.Entity;
import cma.proyectocma.data.model.base.EntityPkSimple;
import cma.proyectocma.domain.common.C;
import cma.proyectocma.domain.mapper.base.Mapper;
import cma.proyectocma.domain.mapper.exception.FromEntityPkSimpleException;
import cma.proyectocma.domain.mapper.exception.ToEntityPkSimpleException;
import cma.proyectocma.domain.mapper.util.IdResolver;
import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.IdReference;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class PkSimpleMapper<D extends Record, E extends EntityPkSimple> extends Mapper<D, E> {

    Class<D> dtoClass;
    Class<E> entityClass;

    public PkSimpleMapper(Class<D> dtoClass, Class<E> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    public D fromEntity(E entity) {
        try {
            return getDtoConstructor(dtoClass).newInstance(valuesFromEntity(dtoClass, entity, entityClass));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new FromEntityPkSimpleException(e);
        }
    }

    public E toEntity(D dto) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            valuesToEntity(dto, entity);
            return entity;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new ToEntityPkSimpleException(e);
        }
    }

    private void valuesToEntity(D dto, E entity) {
        Arrays.stream(dtoClass.getRecordComponents()).forEach(component -> {
            IdReference idReference = component.getAnnotation(IdReference.class);
            try {
                if (idReference != null) {
                    final String entityFieldName;
                    String referencedEntityFieldName = idReference.value().getEntityName();
                    Field entityField = entityClass.getDeclaredField(referencedEntityFieldName);
                    if (component.isAnnotationPresent(DtoId.class)) entityFieldName = C.ID;
                    else entityFieldName = entityField.getName();
                        setValue(entity, entityFieldName, new IdResolver<>(
                            (Class<? extends Entity>) entityField.getType()
                    ).resolve(
                            (Integer) component.getAccessor().invoke(dto)
                    ));
                } else setValue(entity, component.getName(), component.getAccessor().invoke(dto));
            } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException e) {
                throw new ToEntityPkSimpleException(e);
            }
        });
    }

}

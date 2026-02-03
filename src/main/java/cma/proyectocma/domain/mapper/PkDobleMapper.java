package cma.proyectocma.domain.mapper;

import cma.proyectocma.data.model.base.EntityPkDoble;
import cma.proyectocma.data.model.base.EntityPkSimple;
import cma.proyectocma.domain.mapper.base.Mapper;
import cma.proyectocma.domain.mapper.exception.FromEntityPkSimpleException;
import cma.proyectocma.domain.mapper.exception.ToEntityPkDobleException;
import cma.proyectocma.domain.mapper.util.IdResolver;
import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.IdReference;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class PkDobleMapper<D extends Record, E extends EntityPkDoble> extends Mapper<D, E> {

    private final Class<D> dtoClass;
    private final Class<E> entityClass;

    public PkDobleMapper(Class<D> dtoClass, Class<E> entityClass) {
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
            EntityPkDoble.PkDoble pk = EntityPkDoble.PkDoble.class.getDeclaredConstructor().newInstance();
            Arrays.stream(dto.getClass().getRecordComponents()).forEach(component -> {
                DtoId dtoIdAnnotation = component.getAnnotation(DtoId.class);
                if (dtoIdAnnotation == null) return;
                setValue(pk, dtoIdAnnotation.value().getIdName(), getValue(dto, component.getName()).orElseThrow());
            });
            entity.setId(pk);
            Arrays.stream(dto.getClass().getRecordComponents())
                    .filter(component -> !component.isAnnotationPresent(DtoId.class))
                    .forEach(component -> {
                        IdReference idReference = component.getAnnotation(IdReference.class);
                        try {
                            if (idReference != null)
                                setValue(entity, idReference.value().getEntityName(), new IdResolver<>(
                                        (Class<EntityPkSimple>) getValue(entity, idReference.value().getEntityName()).orElseThrow(() ->
                                                new ToEntityPkDobleException("")
                                        ).getClass()
                                ).resolve(
                                        (Integer) component.getAccessor().invoke(dto)
                                ));
                            else
                                setValue(entity, component.getName(), getValue(dto, dtoClass.getField(component.getName())));
                        } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException e) {
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

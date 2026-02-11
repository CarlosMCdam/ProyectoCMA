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

/**
 * Mapper universal para entidades JPA con clave primaria simple.
 *
 * @param <D>
 * @param <E>
 */
public final class PkSimpleMapper<D extends Record, E extends EntityPkSimple> extends Mapper<D, E> {

    /**
     * Clase del DTO.
     */
    Class<D> dtoClass;

    /**
     * Clase de la entidad.
     */
    Class<E> entityClass;

    /**
     * Constructor completo.
     *
     * @param dtoClass    Clase del DTO.
     * @param entityClass Clase de la entidad.
     */
    public PkSimpleMapper(Class<D> dtoClass, Class<E> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }

    /**
     * Mapea de entidad a DTO.
     *
     * @param entity Entidad.
     * @return DTO.
     */
    public D fromEntity(E entity) {
        try {
            return getDtoConstructor(dtoClass).newInstance(valuesFromEntity(dtoClass, entity, entityClass));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new FromEntityPkSimpleException(e);
        }
    }

    /**
     * Mapea de DTO a entidad.
     *
     * @param dto DTO.
     * @return Entidad.
     */
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

    /**
     * Obtiene los valores de los atributos del DTO y los aplica a los de la entidad.
     *
     * @param dto    DTO.
     * @param entity Entidad.
     */
    @Override
    protected void valuesToEntity(D dto, E entity) {
        Arrays.stream(dtoClass.getRecordComponents()).forEach(component -> {
            IdReference idReference = component.getAnnotation(IdReference.class);
            try {
                if (idReference != null) {
                    final String entityFieldName;
                    final Field entityField = entityClass.getDeclaredField(idReference.value().getEntityName());
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

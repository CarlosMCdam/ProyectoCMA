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

/**
 * Mapper universal para entidades JPA con clave primaria compuesta.
 *
 * @param <D> DTO.
 * @param <E> Entidad.
 */
public final class PkDobleMapper<D extends Record, E extends EntityPkDoble> extends Mapper<D, E> {

    /**
     * Clase del DTO.
     */
    private final Class<D> dtoClass;
    /**
     * Clase de la entidad.
     */
    private final Class<E> entityClass;

    /**
     * Constructor completo.
     *
     * @param dtoClass    Clase del DTO.
     * @param entityClass Clase de la entidad.
     */
    public PkDobleMapper(Class<D> dtoClass, Class<E> entityClass) {
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
     * Se obtiene el constructor vacío, se construye e instancia la clave primaria de la entidad a partir de los atributos
     * marcados como @DtoId en el DTO, y se mapean los demás atributos.
     *
     * @param dto DTO.
     * @return Entidad.
     */
    public E toEntity(D dto) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            valuesToEntity(dto, entity);
            return entity;
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new ToEntityPkDobleException(e);
        }
    }

    @Override
    protected void valuesToEntity(D dto, E entity) {
        try {
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
                                        (Class<EntityPkSimple>) getValue(entity, idReference.value().getEntityName()).orElseThrow().getClass()
                                ).resolve(
                                        (Integer) component.getAccessor().invoke(dto)
                                ));
                            else
                                setValue(entity, component.getName(), getValue(dto, component.getName()).orElse(null));
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new ToEntityPkDobleException(e);
                        }
                    });
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                IllegalAccessException e) {
            throw new ToEntityPkDobleException(e);
        }
    }

}

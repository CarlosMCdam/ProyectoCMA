package cma.proyectocma.domain.mapper.base;

import cma.proyectocma.Launcher;
import cma.proyectocma.data.model.base.Entity;
import cma.proyectocma.data.model.base.EntityPkDoble;
import cma.proyectocma.data.model.base.EntityPkSimple;
import cma.proyectocma.domain.mapper.PkDobleMapper;
import cma.proyectocma.domain.mapper.PkSimpleMapper;
import cma.proyectocma.domain.mapper.exception.base.MapperException;
import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.domain.model.util.IdReference;
import jakarta.persistence.MapsId;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Superclase de los mappers conversores de entidades JPA a DTOs del modelo.
 *
 * @param <D> DTO.
 * @param <E> Entidad.
 */
@SuppressWarnings("java:119")
public abstract sealed class Mapper<D extends Record, E extends Entity> permits PkSimpleMapper, PkDobleMapper {

    /**
     * Mapea de entidad a DTO.
     * Se delega la lógica a las clases hijas donde existe un atributo para la clase del DTO y la entidad.
     * La implementación es la misma.
     *
     * @param entity Entidad.
     * @return DTO.
     */
    public abstract D fromEntity(E entity);

    /**
     * Devuelve un array con todos los valores de los atributos de la entidad ordenados por como aparecen en el DTO.
     * <p>
     * Si el atributo coincide en tipos, se mapea directamente (String -> String).
     * <p>
     * Si el atributo está marcado con @IdReference, representa el valor de la clave primaria de la entidad
     * referenciada en la entidad siendo mapeada (Dispositivo -> Integer).
     * <p>
     * Si además de ser una referencia, el atributo es el identificador del DTO y la clave primaria de la entidad
     * no es a la vez clave foránea, se obtiene el valor de la clave primaria de la superclase de la entidad.
     *
     * @param dtoClass    Clase del DTO.
     * @param entity      Entidad.
     * @param entityClass Clase de la Entidad.
     * @return Array de valores.
     */
    protected <Simple extends EntityPkSimple, Doble extends EntityPkDoble> Object[] valuesFromEntity(
            Class<D> dtoClass, E entity, Class<E> entityClass
    ) {
        return mapDtoComponents(dtoClass, component -> {
            final DtoId dtoId = component.getAnnotation(DtoId.class);
            final IdReference idReference = component.getAnnotation(IdReference.class);
            if (dtoId != null) {
                if (entity instanceof EntityPkSimple entityPkSimple) return getIdFromEntity(entityPkSimple);
                return getIdFromEntity((Doble) entity, dtoId.value().getIdName());
            }
            if (idReference != null)
                return getIdFromEntity((Simple) getValue(entity, idReference.value().getEntityName()).orElseThrow());
            return getValue(entity, component.getName()).orElse(null);
        }).toArray();
    }

    /**
     * Mapea de DTO a entidad.
     * Se delega la lógica a las clases hijas donde existe un atributo para la clase del DTO y la entidad.
     *
     * @param dto DTO.
     * @return Entidad.
     */
    public abstract E toEntity(D dto);

    protected abstract void valuesToEntity(D dto, E entity);

    /**
     * Obtiene el valor del campo de un objeto a través de su getter.
     * Respeta la convención de nombres de los getters de atributos booleanos.
     *
     * @param origin Objeto de donde se obtiene el atributo.
     * @param field  Atributo de donde se obtiene el valor.
     * @return Valor.
     */
    protected Optional<Object> getValue(Object origin, String fieldName) {
        try {
            return Optional.ofNullable(origin.getClass().getMethod("get" + Launcher.capitalize(fieldName)).invoke(origin));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException e) {
            throw new MapperException(e);
        }
    }

    /**
     * Obtiene el identificaor de la superclase de una entidad a través de su getter.
     *
     * @param entity Entidad de donde se obtiene el atributo.
     * @param field  Atributo de donde se obtiene el identificador.
     * @return Identificador.
     */
    protected <S extends EntityPkSimple, Id extends Serializable> Id getIdFromEntity(S entity) {
        try {
            if (isSuperclassIdFieldMapped(entity.getClass()))
                return (Id) Arrays.stream(entity.getClass().getDeclaredFields())
                        .filter(field -> field.isAnnotationPresent(MapsId.class))
                        .findFirst()
                        .map(field -> (S) getValue(entity, field.getName()).orElseThrow())
                        .orElseThrow()
                        .getId();
            return (Id) entity.getId();
        } catch (SecurityException | IllegalArgumentException e) {
            throw new MapperException(e);
        }
    }

    protected <Simple extends EntityPkSimple, Doble extends EntityPkDoble, Id extends Serializable> Id getIdFromEntity(
            Doble entity, String pkFragment
    ) {
        return (Id) Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> {
                    final MapsId mapsId = field.getAnnotation(MapsId.class);
                    return mapsId != null && mapsId.value().equals(pkFragment);
                })
                .findFirst()
                .map(field -> (Simple) getValue(entity, field.getName()).orElseThrow())
                .orElseThrow()
                .getId();
    }

    /**
     * Cambia el valor de un atributo de un objeto a través de su setter.
     *
     * @param target Objeto de donde se obtiene el atributo.
     * @param field  Atributo donde se cambia el valor.
     * @param value  Valor.
     */
    protected void setValue(Object target, Field field, Object value) {
        final Class<?> targetClass = target.getClass();
        try {
            targetClass.getMethod("set" + Launcher.capitalize(field.getName()), field.getType()).invoke(target, value);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException e) {
            throw new MapperException(e);
        }
    }

    /**
     * Cambia el valor de un atributo de un objeto a través de su setter.
     *
     * @param target    Objeto de donde se obtiene el atributo.
     * @param fieldName Nombre del atributo donde se cambia el valor.
     * @param value     Valor.
     */
    protected void setValue(Object target, String fieldName, Object value) {
        final Class<?> targetClass = target.getClass();
        try {
            Arrays.stream(targetClass.getMethods())
                    .filter(method -> method.getName().equals("set" + Launcher.capitalize(fieldName)) && method.getParameterCount() == 1)
                    .findFirst()
                    .orElseThrow(() -> new MapperException(""))
                    .invoke(target, value);
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException e) {
            throw new MapperException(e);
        }
    }

    /**
     * Obtiene la entidad referenciada en otra entidad casteada a su correspondiente clase.
     *
     * @param origin               Entidad.
     * @param referencedEntityName Nombre de la entidad referenciada.
     * @return Entidad referenciada.
     */
    protected <R extends Entity> R getReferencedEntity(E origin, String referencedEntityName) {
        Object referenced = getValue(origin, referencedEntityName).orElseThrow();
        if (!(referenced instanceof Entity)) throw new MapperException("");
        return (R) referenced;
    }

    /**
     * @param entityClass Clase de la entidad.
     * @return Si la clave primaria de la clase de la entidad es a su vez clave foránea.
     */
    protected <S extends Entity> boolean isSuperclassIdFieldMapped(Class<S> entityClass) {
        return Arrays.stream(entityClass.getDeclaredFields())
                .anyMatch(field -> field.isAnnotationPresent(MapsId.class));
    }

    /**
     * Mapea con la función pasada sobre los RecordComponents del DTO y devuelve un objeto del tipo de retorno
     * definido en la función.
     *
     * @param dtoClass Clase del DTO.
     * @param mapper   Función.
     * @param <R>      Tipo de retorno.
     * @return Objeto.
     */
    private <R> Stream<R> mapDtoComponents(Class<D> dtoClass, Function<RecordComponent, R> mapper) {
        return Arrays.stream(dtoClass.getRecordComponents()).map(mapper);
    }

    /**
     * Devuelve el constructor canónico del DTO.
     *
     * @param dtoClass Clase del DTO.
     * @return Constructor.
     */
    protected Constructor<D> getDtoConstructor(Class<D> dtoClass) {
        try {
            return dtoClass.getDeclaredConstructor(mapDtoComponents(dtoClass, RecordComponent::getType).toArray(Class<?>[]::new));
        } catch (NoSuchMethodException ignored) {
            return null;
        }
    }

}

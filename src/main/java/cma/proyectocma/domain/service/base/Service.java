package cma.proyectocma.domain.service.base;

import cma.proyectocma.data.model.base.Entity;
import cma.proyectocma.data.repository.Repository;
import cma.proyectocma.domain.mapper.base.Mapper;
import cma.proyectocma.domain.service.PkDobleService;
import cma.proyectocma.domain.service.PkSimpleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Superclase de los servicios que separan la lógica de la capa gráfica y el repositorio.
 * @param <D> DTO.
 * @param <E> Entidad JPA.
 * @param <M> Mapper conversor de entidad JPA a DTO del modelo.
 * @param <Id> Identificador de la entidad JPA.
 */
@SuppressWarnings("java:S119")
@AllArgsConstructor
public abstract sealed class Service<D extends Record, E extends Entity, M extends Mapper<D, E>, Id extends Serializable>
        permits PkSimpleService, PkDobleService {

    /**
     * Repositorio.
     */
    protected final Repository<E, Id> repository;
    /**
     * Mapper.
     */
    protected final M mapper;

    /**
     * @return Lista de todas las entidades.
     */
    public abstract List<D> findAll();

    /**
     * @param ids Uno o varios identificadores de la entidad.
     * @return Entidad.
     */
    public abstract D findById(Integer... ids);

    /**
     * Actualiza una entidad existente.
     * @param dto Dto correspondiente.
     * @return Dto correspondiente.
     */
    public abstract D update(D dto);

}

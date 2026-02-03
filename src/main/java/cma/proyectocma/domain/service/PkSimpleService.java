package cma.proyectocma.domain.service;

import cma.proyectocma.data.model.base.EntityPkSimple;
import cma.proyectocma.data.repository.Repository;
import cma.proyectocma.domain.mapper.PkSimpleMapper;
import cma.proyectocma.domain.service.base.Service;

import java.util.List;

/**
 * Servicio universal para entidades JPA con clave primaria simple.
 * @param <D> DTO.
 * @param <E> Entidad.
 */
public final class PkSimpleService<D extends Record, E extends EntityPkSimple> extends Service<D, E, PkSimpleMapper<D, E>, Integer> {

    /**
     * Constructor que invoca al constructor completo de la superclase.
     * @param repository Repositorio.
     * @param mapper Mapper.
     */
    public PkSimpleService(Repository<E, Integer> repository, PkSimpleMapper<D, E> mapper) {
        super(repository, mapper);
    }

    /**
     * @return Lista de todas las entidades.
     */
    public List<D> findAll() {
        return repository.findAll().stream().map(entity -> {
            try {
                return mapper.fromEntity(entity);
            } catch (SecurityException | IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    /**
     * @param ids Identificador de la entidad.
     * @return Entidad.
     */
    public D findById(Integer... ids) {
        return mapper.fromEntity(repository.findById(ids[0]).orElseThrow());
    }

    /**
     * Actualiza una entidad existente.
     * @param dto Dto correspondiente.
     * @return Dto correspondiente.
     */
    public D update(D dto) {
        System.out.println(dto);
        System.out.println(mapper.toEntity(dto));
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

}


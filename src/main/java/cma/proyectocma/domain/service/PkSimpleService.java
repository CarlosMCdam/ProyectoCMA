package cma.proyectocma.domain.service;

import cma.proyectocma.data.model.base.EntityPkSimple;
import cma.proyectocma.data.repository.Repository;
import cma.proyectocma.domain.mapper.PkSimpleMapper;
import cma.proyectocma.domain.service.base.Service;

import java.util.List;

public final class PkSimpleService<D extends Record, E extends EntityPkSimple> extends Service<D, E, PkSimpleMapper<D, E>, Integer> {

    public PkSimpleService(Repository<E, Integer> repository, PkSimpleMapper<D, E> mapper) {
        super(repository, mapper);
    }

    public List<D> findAll() {
        return repository.findAll().stream().map(entity -> {
            try {
                return mapper.fromEntity(entity);
            } catch (SecurityException | IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    public D findById(Integer... ids) {
        return mapper.fromEntity(repository.findById(ids[0]).orElseThrow());
    }

    public D update(D dto) {
        System.out.println(dto);
        System.out.println(mapper.toEntity(dto));
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

}


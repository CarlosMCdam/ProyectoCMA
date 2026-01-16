package cma.proyectocma.domain.service.base;

import cma.proyectocma.dao.repository.base.PkSimpleRepository;
import cma.proyectocma.dao.model.base.PkSimpleEntity;
import cma.proyectocma.domain.mapper.base.PkSimpleMapper;
import cma.proyectocma.domain.service.exception.ServicePkSimpleException;

import java.util.List;

public abstract class PkSimpleService<T extends Record, E extends PkSimpleEntity> {

    private final PkSimpleRepository<E> repository;
    private final PkSimpleMapper<T, E> mapper;

    protected PkSimpleService(PkSimpleRepository<E> repository, PkSimpleMapper<T, E> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<T> findAll() {
        return repository.findAll().stream().map(entity -> {
            try {
                return mapper.fromEntity(entity);
            } catch (SecurityException | IllegalArgumentException e) {
                throw new ServicePkSimpleException(e);
            }
        }).toList();
    }

    public T findById(Integer id) {
        return mapper.fromEntity(repository.findById(id).orElseThrow());
    }

}


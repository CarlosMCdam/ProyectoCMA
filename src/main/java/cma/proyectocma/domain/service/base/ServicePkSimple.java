package cma.proyectocma.domain.service.base;

import cma.proyectocma.dao.repository.base.RepositoryPkSimple;
import cma.proyectocma.dao.model.base.EntityPkSimple;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class ServicePkSimple<T extends Record, E extends EntityPkSimple> {

    private final Class<T> dtoClass;
    private final RepositoryPkSimple<E> repository;
    private final MapperPkSimple<T, E> mapper;

    public ServicePkSimple(Class<T> dtoClass, RepositoryPkSimple<E> repository, MapperPkSimple<T, E> mapper) {
        this.dtoClass = dtoClass;
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<T> findAll() {
        return repository.findAll().stream().map(entity -> {
            try {
                return mapper.fromEntity(entity);
            } catch (SecurityException | IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    public E findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}


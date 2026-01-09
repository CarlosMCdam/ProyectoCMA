package cma.proyectocma.domain.service.base;

import cma.proyectocma.dao.RepositoryPkSimple;
import cma.proyectocma.dao.model.base.EntityPkSimple;
import cma.proyectocma.domain.mapper.base.MapperPkSimple;

import java.util.List;

public abstract class ServicePkSimple<T extends Record, E extends EntityPkSimple> {

    private final Class<T> dtoClass;
    private final RepositoryPkSimple<E> repository;
    private final MapperPkSimple<T, E> mapper;

    public ServicePkSimple(Class<T> dtoClass, RepositoryPkSimple<E> repository, MapperPkSimple<T, E> mapper) {
        this.repository = repository;
        this.dtoClass = dtoClass;
        this.mapper = mapper;
    }

    public List<T> findAll() {
        return repository.findAll().stream().map(entity -> {
            try {
                T dto = dtoClass.getDeclaredConstructor().newInstance();
                return mapper.fromEntity(entity, dtoClass);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    public E findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

}


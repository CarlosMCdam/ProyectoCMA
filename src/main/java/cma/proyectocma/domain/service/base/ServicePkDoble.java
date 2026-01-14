package cma.proyectocma.domain.service.base;

import cma.proyectocma.dao.repository.base.RepositoryPkDoble;
import cma.proyectocma.dao.model.base.EntityPkDoble;
import cma.proyectocma.domain.mapper.base.MapperPkDoble;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class ServicePkDoble<T extends Record, E extends EntityPkDoble> {

    private final Class<T> dtoClass;
    private final RepositoryPkDoble<E> repository;
    private final MapperPkDoble<T, E> mapper;

    public ServicePkDoble(Class<T> dtoClass, RepositoryPkDoble<E> repository, MapperPkDoble<T, E> mapper) {
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

    public E findById(Integer id1, Integer id2) {
        return repository.findById(new EntityPkDoble.PkDoble(id1, id2)).orElse(null);
    }
}

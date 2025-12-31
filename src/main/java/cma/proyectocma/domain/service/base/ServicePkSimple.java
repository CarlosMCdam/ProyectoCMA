package cma.proyectocma.domain.service.base;

import cma.proyectocma.dao.RepositoryPkSimple;
import cma.proyectocma.dao.model.base.EntityPkSimple;
import cma.proyectocma.domain.model.base.DtoPkSimple;

import java.util.List;

public class ServicePkSimple<T extends DtoPkSimple<T, E>, E extends EntityPkSimple> {

    private final RepositoryPkSimple<E> repository;
    private final Class<T> dtoClass;

    public ServicePkSimple(RepositoryPkSimple<E> repository, Class<T> dtoClass) {
        this.repository = repository;
        this.dtoClass = dtoClass;
    }

    public List<T> findAll() {
        return repository.findAll().stream().map(entity -> {
            try {
                T dto = dtoClass.getDeclaredConstructor().newInstance();
                return dto.fromEntity(entity, dtoClass);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    public E findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

}


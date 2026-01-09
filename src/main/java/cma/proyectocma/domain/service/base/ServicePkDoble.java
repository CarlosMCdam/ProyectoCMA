package cma.proyectocma.domain.service.base;

import cma.proyectocma.dao.RepositoryPkDoble;
import cma.proyectocma.dao.model.base.EntityPkDoble;
import cma.proyectocma.domain.mapper.base.MapperPkDoble;

import java.util.List;

public abstract class ServicePkDoble<T extends MapperPkDoble<T, E>, E extends EntityPkDoble> {

    private final RepositoryPkDoble<E> repository;
    private final Class<T> dtoClass;

    public ServicePkDoble(RepositoryPkDoble<E> repository, Class<T> dtoClass) {
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

    public E findById(Integer id1, Integer id2) {
        return repository.findById(new EntityPkDoble.PkDoble(id1, id2)).orElse(null);
    }
}

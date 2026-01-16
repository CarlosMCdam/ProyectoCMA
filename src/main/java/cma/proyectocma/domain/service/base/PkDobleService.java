package cma.proyectocma.domain.service.base;

import cma.proyectocma.dao.repository.base.PkDobleRepository;
import cma.proyectocma.dao.model.base.PkDobleEntity;
import cma.proyectocma.domain.mapper.base.PkDobleMapper;
import cma.proyectocma.domain.service.exception.ServicePkDobleException;

import java.util.List;

public abstract class PkDobleService<T extends Record, E extends PkDobleEntity> {

    private final PkDobleRepository<E> repository;
    private final PkDobleMapper<T, E> mapper;

    protected PkDobleService(PkDobleRepository<E> repository, PkDobleMapper<T, E> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<T> findAll() {
        return repository.findAll().stream().map(entity -> {
            try {
                return mapper.fromEntity(entity);
            } catch (SecurityException | IllegalArgumentException e) {
                throw new ServicePkDobleException(e);
            }
        }).toList();
    }

    public T findById(Integer id1, Integer id2) {
        return mapper.fromEntity(repository.findById(new PkDobleEntity.PkDoble(id1, id2)).orElseThrow());
    }

}

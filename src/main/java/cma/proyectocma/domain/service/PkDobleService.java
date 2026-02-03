package cma.proyectocma.domain.service;

import cma.proyectocma.data.model.base.EntityPkDoble;
import cma.proyectocma.data.repository.Repository;
import cma.proyectocma.domain.mapper.PkSimpleMapper;
import cma.proyectocma.domain.mapper.PkDobleMapper;
import cma.proyectocma.domain.service.base.Service;
import cma.proyectocma.domain.service.exception.base.ServiceException;

import java.util.List;

public final class PkDobleService<D extends Record, E extends EntityPkDoble> extends Service<D, E, PkDobleMapper<D, E>, EntityPkDoble.PkDoble> {

    public PkDobleService(Repository<E, EntityPkDoble.PkDoble> repository, PkDobleMapper<D, E> mapper) {
        super(repository, mapper);
    }

    public List<D> findAll() {
        return repository.findAll().stream().map(entity -> {
            try {
                return mapper.fromEntity(entity);
            } catch (SecurityException | IllegalArgumentException e) {
                throw new ServiceException(e);
            }
        }).toList();
    }

    public D findById(Integer... ids) {
        return mapper.fromEntity(repository.findById(new EntityPkDoble.PkDoble(ids[0], ids[1])).orElseThrow());
    }

    public D update(D dto) {
        return mapper.fromEntity(repository.save(mapper.toEntity(dto)));
    }

}

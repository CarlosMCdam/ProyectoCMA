package cma.proyectocma.domain.service.base;

import cma.proyectocma.data.model.base.Entity;
import cma.proyectocma.data.repository.Repository;
import cma.proyectocma.domain.mapper.base.Mapper;
import cma.proyectocma.domain.service.PkDobleService;
import cma.proyectocma.domain.service.PkSimpleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("java:S119")

@AllArgsConstructor
public abstract sealed class Service<D extends Record, E extends Entity, M extends Mapper<D, E>, Id extends Serializable>
        permits PkSimpleService, PkDobleService {

    protected final Repository<E, Id> repository;
    protected final M mapper;

    public abstract List<D> findAll();

    public abstract D findById(Integer... ids);

    public abstract D update(D dto);

}

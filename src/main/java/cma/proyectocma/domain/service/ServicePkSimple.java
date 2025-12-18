package cma.proyectocma.domain.service;

import cma.proyectocma.dao.model.base.EntityPkSimple;
import cma.proyectocma.dao.RepositoryPkSimple;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePkSimple<T extends EntityPkSimple> {

    private final RepositoryPkSimple repository;

    public ServicePkSimple(RepositoryPkSimple repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findById(Integer id) {
        return (T) repository.findById(id).orElse(null);
    }
}

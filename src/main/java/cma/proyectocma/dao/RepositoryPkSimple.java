package cma.proyectocma.dao;

import cma.proyectocma.dao.model.base.EntityPkSimple;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPkSimple<T extends EntityPkSimple> extends JpaRepository<T, Integer> {
    default boolean existsAndNotNull(Integer id) {
        return findById(id).isPresent();
    }
}

package cma.proyectocma.dao.repository.base;

import cma.proyectocma.dao.model.*;
import cma.proyectocma.dao.model.base.EntityPkSimple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryPkSimple<T extends EntityPkSimple> extends JpaRepository<T, Integer> {

    default boolean existsAndNotNull(Integer id) {
        return findById(id).isPresent();
    }

}

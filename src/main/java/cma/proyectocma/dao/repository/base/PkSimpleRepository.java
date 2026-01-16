package cma.proyectocma.dao.repository.base;

import cma.proyectocma.dao.model.base.PkSimpleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PkSimpleRepository<T extends PkSimpleEntity> extends JpaRepository<T, Integer> {

    default boolean existsAndNotNull(Integer id) {
        return findById(id).isPresent();
    }

}

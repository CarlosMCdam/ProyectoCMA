package cma.proyectocma.dao.repository.base;

import cma.proyectocma.dao.model.base.PkDobleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PkDobleRepository<T extends PkDobleEntity> extends JpaRepository<T, PkDobleEntity.PkDoble> {

    default boolean existsAndNotNull(Integer id1, Integer id2) {
        return findById(new PkDobleEntity.PkDoble(id1, id2)).isPresent();
    }

}

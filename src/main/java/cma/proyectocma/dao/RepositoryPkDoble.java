package cma.proyectocma.dao;

import cma.proyectocma.dao.model.PersonaDispositivo;
import cma.proyectocma.dao.model.base.EntityPkDoble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryPkDoble<T extends EntityPkDoble> extends JpaRepository<T, EntityPkDoble.PkDoble> {

    default boolean existsAndNotNull(Integer id1, Integer id2) {
        return findById(new EntityPkDoble.PkDoble(id1, id2)).isPresent();
    }

    interface RepositoryPersonaDispositivo extends RepositoryPkDoble<PersonaDispositivo> {}
}

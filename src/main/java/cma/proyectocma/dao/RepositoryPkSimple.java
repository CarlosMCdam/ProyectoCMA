package cma.proyectocma.dao;

import cma.proyectocma.dao.model.*;
import cma.proyectocma.dao.model.base.EntityPkSimple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryPkSimple<T extends EntityPkSimple> extends JpaRepository<T, Integer> {

    default boolean existsAndNotNull(Integer id) {
        return findById(id).isPresent();
    }

    interface RepositoryArmarioCarga extends RepositoryPkSimple<ArmarioCarga> {}
    interface RepositoryAveria extends RepositoryPkSimple<Averia> {}
    interface RepositoryDispositivo extends RepositoryPkSimple<Dispositivo> {}
    interface RepositoryImpresora extends RepositoryPkSimple<Impresora> {}
    interface RepositoryModelo extends RepositoryPkSimple<Modelo> {}
    interface RepositoryPantallaTactil extends RepositoryPkSimple<PantallaTactil> {}
    interface RepositoryPc extends RepositoryPkSimple<Pc> {}
    interface RepositoryPersona extends RepositoryPkSimple<Persona> {}
    interface RepositoryPortatil extends RepositoryPkSimple<Portatil> {}
    interface RepositoryTablet extends RepositoryPkSimple<Tablet> {}
}

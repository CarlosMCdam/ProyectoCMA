package cma.proyectocma.domain.service.base;

import cma.proyectocma.dao.RepositoryPkDoble;
import cma.proyectocma.dao.model.base.EntityPkDoble;
import cma.proyectocma.domain.model.base.DtoPkDoble;

public class ServicePkDoble<T extends DtoPkDoble<T, E>, E extends EntityPkDoble> {

    private final RepositoryPkDoble repositoryPkDoble;
    private final Class<T> dtoClass;

    public ServicePkDoble(RepositoryPkDoble repositoryPkDoble, Class<T> dtoClass) {
        this.repositoryPkDoble = repositoryPkDoble;
        this.dtoClass = dtoClass;
    }

}

package cma.proyectocma.domain.mapper.base.util;

import cma.proyectocma.dao.repository.base.RepositoryPkDoble;
import cma.proyectocma.dao.repository.base.RepositoryPkSimple;
import cma.proyectocma.dao.model.base.EntityPkDoble;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public final class IdResolver {

    private final Map<Class<?>, RepositoryPkSimple<?>> simpleRepos = new HashMap<>();
    private final Map<Class<?>, RepositoryPkDoble<?>> dobleRepos = new HashMap<>();

    public IdResolver(List<RepositoryPkSimple<?>> reposSimple, List<RepositoryPkDoble<?>> reposDoble) {
        for (RepositoryPkSimple<?> repo : reposSimple) {
            Class<?> entityClass = extractEntityClass(repo);
            simpleRepos.put(entityClass, repo);
        }

        // Indexar repositorios de PK doble
        for (RepositoryPkDoble<?> repo : reposDoble) {
            Class<?> entityClass = extractEntityClass(repo);
            dobleRepos.put(entityClass, repo);
        }
    }

    private Class<?> extractEntityClass(Object repo) {
        // Intentar como repositorio simple
        ResolvableType type = ResolvableType.forClass(repo.getClass()).as(RepositoryPkSimple.class);
        if (type.hasGenerics()) {
            return type.getGeneric(0).resolve();
        }

        // Intentar como repositorio doble
        type = ResolvableType.forClass(repo.getClass()).as(RepositoryPkDoble.class);
        if (type.hasGenerics()) {
            return type.getGeneric(0).resolve();
        }

        throw new IllegalStateException("No se pudo determinar la clase de entidad para " + repo.getClass());
    }


    public Object resolveSimple(Class<?> entityType, Integer id) {
        RepositoryPkSimple<?> repo = simpleRepos.get(entityType);
        if (repo == null)
            throw new IllegalArgumentException("No existe repositorio para " + entityType.getSimpleName());
        return repo.getReferenceById(id);
    }

    public Object resolveDoble(Class<?> entityType, Integer id1, Integer id2) {
        RepositoryPkDoble<?> repo = dobleRepos.get(entityType);
        if (repo == null)
            throw new IllegalArgumentException("No existe repositorio para " + entityType.getSimpleName());

        EntityPkDoble.PkDoble pk = new EntityPkDoble.PkDoble(id1, id2);
        return repo.getReferenceById(pk);
    }
}

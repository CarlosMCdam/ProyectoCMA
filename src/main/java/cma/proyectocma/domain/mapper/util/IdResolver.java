package cma.proyectocma.domain.mapper.util;

import cma.proyectocma.dao.RepositoryPkSimple;
import cma.proyectocma.dao.RepositoryPkDoble;
import cma.proyectocma.dao.model.base.EntityPkDoble;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class IdResolver {

    private final Map<Class<?>, RepositoryPkSimple<?>> simpleRepos = new HashMap<>();
    private final Map<Class<?>, RepositoryPkDoble<?>> dobleRepos = new HashMap<>();

    public IdResolver(
            List<RepositoryPkSimple<?>> reposSimple,
            List<RepositoryPkDoble<?>> reposDoble
    ) {
        // Indexar repositorios de PK simple
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

    /**
     * Extrae la clase de la entidad gestionada por un repositorio.
     */
    private Class<?> extractEntityClass(Object repo) {
        for (Type type : repo.getClass().getGenericInterfaces()) {
            if (type instanceof ParameterizedType pt) {
                Type raw = pt.getRawType();
                if (raw instanceof Class<?> rawClass &&
                        (RepositoryPkSimple.class.isAssignableFrom(rawClass)
                                || RepositoryPkDoble.class.isAssignableFrom(rawClass))) {

                    Type entityType = pt.getActualTypeArguments()[0];
                    return (Class<?>) entityType;
                }
            }
        }
        throw new IllegalStateException("No se pudo determinar la clase de entidad para " + repo.getClass());
    }

    /**
     * Resolver para PK simple.
     */
    public Object resolveSimple(Class<?> entityType, Object id) {
        RepositoryPkSimple<?> repo = simpleRepos.get(entityType);
        if (repo == null)
            throw new IllegalArgumentException("No existe repositorio para " + entityType.getSimpleName());

        return repo.getReferenceById(id);
    }

    /**
     * Resolver para PK doble.
     */
    public Object resolveDoble(Class<?> entityType, Object id1, Object id2) {
        RepositoryPkDoble<?> repo = dobleRepos.get(entityType);
        if (repo == null)
            throw new IllegalArgumentException("No existe repositorio para " + entityType.getSimpleName());

        EntityPkDoble.PkDoble pk = new EntityPkDoble.PkDoble(id1, id2);
        return repo.getReferenceById(pk);
    }
}

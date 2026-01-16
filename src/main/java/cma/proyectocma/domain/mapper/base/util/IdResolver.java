package cma.proyectocma.domain.mapper.base.util;

import cma.proyectocma.dao.model.base.PkDobleEntity;
import cma.proyectocma.dao.repository.base.PkDobleRepository;
import cma.proyectocma.dao.repository.base.PkSimpleRepository;
import cma.proyectocma.domain.common.C;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public final class IdResolver {

    private final Map<Class<?>, PkSimpleRepository<?>> simpleRepos = new HashMap<>();
    private final Map<Class<?>, PkDobleRepository<?>> dobleRepos = new HashMap<>();

    public IdResolver(List<PkSimpleRepository<?>> reposSimple, List<PkDobleRepository<?>> reposDoble) {
        for (PkSimpleRepository<?> repo : reposSimple) {
            Class<?> entityClass = extractEntityClass(repo);
            simpleRepos.put(entityClass, repo);
        }
        for (PkDobleRepository<?> repo : reposDoble) {
            Class<?> entityClass = extractEntityClass(repo);
            dobleRepos.put(entityClass, repo);
        }
    }

    private Class<?> extractEntityClass(Object repo) {
        ResolvableType type = ResolvableType.forClass(repo.getClass()).as(PkSimpleRepository.class);
        if (type.hasGenerics()) return type.getGeneric(0).resolve();
        type = ResolvableType.forClass(repo.getClass()).as(PkDobleRepository.class);
        if (type.hasGenerics()) return type.getGeneric(0).resolve();
        throw new IllegalStateException(C.EXCEPTIONMESSAGE_IDRESOLVER_UNKNOWNREPOSITORYCLASS + repo.getClass());
    }


    public Object resolveSimple(Class<?> entityType, Integer id) {
        PkSimpleRepository<?> repo = simpleRepos.get(entityType);
        if (repo == null)
            throw new IllegalArgumentException(C.EXCEPTIONMESSAGE_IDRESOLVER_MISSINGREPOSITORY + entityType.getSimpleName());
        return repo.getReferenceById(id);
    }

    public Object resolveDoble(Class<?> entityType, Integer id1, Integer id2) {
        PkDobleRepository<?> repo = dobleRepos.get(entityType);
        if (repo == null)
            throw new IllegalArgumentException(C.EXCEPTIONMESSAGE_IDRESOLVER_MISSINGREPOSITORY + entityType.getSimpleName());
        return repo.getReferenceById(new PkDobleEntity.PkDoble(id1, id2));
    }
}

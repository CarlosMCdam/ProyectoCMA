package cma.proyectocma.domain.model.base;

import cma.proyectocma.dao.model.base.EntityPkSimple;

import java.util.Arrays;

public interface DtoPkSimple<T extends DtoPkSimple, E extends EntityPkSimple> {
    default T fromEntity(E entity, Class<T> daoClass) {
        try {
            T dao = daoClass.getDeclaredConstructor().newInstance();
            Arrays.stream(entity.getClass().getDeclaredFields()).forEach(field -> {
                field.setAccessible(true);
                try {
                    daoClass.getDeclaredField(field.getName()).set(dao, field.get(entity));
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            });
            return dao;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default E toEntity(T dao, Class<E> entityClass) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            Arrays.stream(entity.getClass().getDeclaredFields()).forEach(field -> {
                field.setAccessible(true);
                try {
                    entityClass.getDeclaredField(field.getName()).set(entity, field.get(entity));
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            });
            return entity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

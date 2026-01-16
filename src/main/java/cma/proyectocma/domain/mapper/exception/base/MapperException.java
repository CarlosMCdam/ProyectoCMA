package cma.proyectocma.domain.mapper.exception.base;

import cma.proyectocma.domain.common.C;
import cma.proyectocma.domain.mapper.exception.*;

import java.util.function.Consumer;

public abstract sealed class MapperException extends RuntimeException
        permits FromEntityPkSimpleException, ToEntityPkSimpleException, FromEntityPkDobleException, ToEntityPkDobleException,
        MapperRegistrarException {

    @SuppressWarnings(C.SONARQUBEFALSEPOSITIVE_UNUSED)
    private final transient Consumer<?> accion;

    protected MapperException(String message) {
        super(message);
        this.accion = null;
    }

    protected MapperException(Exception e) {
        super(e);
        this.accion = null;
    }

    protected MapperException(Exception e, Consumer<?> accion) {
        super(e);
        this.accion = accion;
    }
}

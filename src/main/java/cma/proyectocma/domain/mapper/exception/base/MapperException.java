package cma.proyectocma.domain.mapper.exception.base;

import cma.proyectocma.domain.common.C;
import cma.proyectocma.domain.mapper.exception.FromEntityPkDobleException;
import cma.proyectocma.domain.mapper.exception.FromEntityPkSimpleException;
import cma.proyectocma.domain.mapper.exception.ToEntityPkDobleException;
import cma.proyectocma.domain.mapper.exception.ToEntityPkSimpleException;
import lombok.Getter;

import java.util.function.Consumer;

public abstract sealed class MapperException extends RuntimeException
        permits FromEntityPkSimpleException, ToEntityPkSimpleException, FromEntityPkDobleException, ToEntityPkDobleException {

    @SuppressWarnings(C.SONARQUBEFALSEPOSITIVE_UNUSED)
    private final transient Consumer<?> accion;

    protected MapperException(Exception e) {
        super(e);
        this.accion = null;
    }

    protected MapperException(Exception e, Consumer<?> accion) {
        super(e);
        this.accion = accion;
    }
}

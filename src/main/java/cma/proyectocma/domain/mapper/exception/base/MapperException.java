package cma.proyectocma.domain.mapper.exception.base;

import cma.proyectocma.domain.mapper.exception.FromEntityPkDobleException;
import cma.proyectocma.domain.mapper.exception.FromEntityPkSimpleException;
import cma.proyectocma.domain.mapper.exception.ToEntityPkDobleException;
import cma.proyectocma.domain.mapper.exception.ToEntityPkSimpleException;

import java.util.function.Consumer;

public abstract sealed class MapperException extends RuntimeException
        permits FromEntityPkSimpleException, ToEntityPkSimpleException, FromEntityPkDobleException, ToEntityPkDobleException {

    private final Consumer<?> accion;

    protected MapperException(Exception e) {
        super(e);
        this.accion = null;
    }

    protected MapperException(Exception e, Consumer<?> accion) {
        super(e);
        this.accion = accion;
    }
}

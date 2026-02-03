package cma.proyectocma.domain.mapper.exception.base;

import cma.proyectocma.domain.mapper.exception.FromEntityPkDobleException;
import cma.proyectocma.domain.mapper.exception.FromEntityPkSimpleException;
import cma.proyectocma.domain.mapper.exception.ToEntityPkDobleException;
import cma.proyectocma.domain.mapper.exception.ToEntityPkSimpleException;

import java.util.function.Consumer;

public sealed class MapperException extends RuntimeException
        permits FromEntityPkSimpleException, ToEntityPkSimpleException, FromEntityPkDobleException, ToEntityPkDobleException {

    private final Consumer<?> accion;

    public MapperException(String message) {
        super(message);
        this.accion = null;
    }

    public MapperException(Exception exception) {
        super(exception);
        this.accion = null;
    }

    protected MapperException(Exception exception, Consumer<?> accion) {
        super(exception);
        this.accion = accion;
    }
}

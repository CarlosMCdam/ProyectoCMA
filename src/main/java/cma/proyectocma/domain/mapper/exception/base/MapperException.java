package cma.proyectocma.domain.mapper.exception.base;

import cma.proyectocma.domain.mapper.exception.FromEntityDobleException;
import cma.proyectocma.domain.mapper.exception.FromEntitySimpleException;
import cma.proyectocma.domain.mapper.exception.ToEntityDobleException;
import cma.proyectocma.domain.mapper.exception.ToEntitySimpleException;

import java.util.function.Consumer;

public abstract sealed class MapperException extends RuntimeException
        permits FromEntitySimpleException, ToEntitySimpleException, FromEntityDobleException, ToEntityDobleException {

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

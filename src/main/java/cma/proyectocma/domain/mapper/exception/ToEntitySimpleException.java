package cma.proyectocma.domain.mapper.exception;

import cma.proyectocma.domain.mapper.exception.base.MapperException;

import java.util.function.Consumer;

public final class ToEntitySimpleException extends MapperException {
    public ToEntitySimpleException(Exception e) {
        super(e);
    }

    public ToEntitySimpleException(RuntimeException e, Consumer<?> accion) {
        super(e, accion);
    }
}

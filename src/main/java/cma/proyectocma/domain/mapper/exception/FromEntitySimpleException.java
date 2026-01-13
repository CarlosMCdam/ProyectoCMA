package cma.proyectocma.domain.mapper.exception;

import cma.proyectocma.domain.mapper.exception.base.MapperException;

import java.util.function.Consumer;

public final class FromEntitySimpleException extends MapperException {
    public FromEntitySimpleException(Exception e) {
        super(e);
    }

    public FromEntitySimpleException(Exception e, Consumer<?> accion) {
        super(e, accion);
    }
}

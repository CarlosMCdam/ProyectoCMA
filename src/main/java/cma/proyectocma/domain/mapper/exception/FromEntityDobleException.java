package cma.proyectocma.domain.mapper.exception;

import cma.proyectocma.domain.mapper.exception.base.MapperException;

import java.util.function.Consumer;

public final class FromEntityDobleException extends MapperException {
    public FromEntityDobleException(Exception e) {
        super(e);
    }

    public FromEntityDobleException(RuntimeException e, Consumer<?> accion) {
        super(e, accion);
    }
}

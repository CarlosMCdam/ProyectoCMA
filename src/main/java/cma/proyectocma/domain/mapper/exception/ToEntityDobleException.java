package cma.proyectocma.domain.mapper.exception;

import cma.proyectocma.domain.mapper.exception.base.MapperException;

import java.util.function.Consumer;

public final class ToEntityDobleException extends MapperException {
    public ToEntityDobleException(Exception e) {
        super(e);
    }

    public ToEntityDobleException(RuntimeException e, Consumer<?> accion) {
        super(e, accion);
    }
}

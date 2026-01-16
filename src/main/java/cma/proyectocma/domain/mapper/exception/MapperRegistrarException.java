package cma.proyectocma.domain.mapper.exception;

import cma.proyectocma.domain.mapper.exception.base.MapperException;

import java.util.function.Consumer;

public final class MapperRegistrarException extends MapperException {

    public MapperRegistrarException(String message) {
        super(message);
    }

    public MapperRegistrarException(Exception e) {
        super(e);
    }

    public MapperRegistrarException(Exception e, Consumer<?> accion) {
        super(e, accion);
    }

}

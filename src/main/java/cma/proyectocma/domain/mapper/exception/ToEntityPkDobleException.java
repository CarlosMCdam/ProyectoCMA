package cma.proyectocma.domain.mapper.exception;

import cma.proyectocma.domain.mapper.exception.base.MapperException;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public final class ToEntityPkDobleException extends MapperException {
    public ToEntityPkDobleException(Exception e) {
        super(e);
    }

    public ToEntityPkDobleException(Exception e, Consumer<?> accion) {
        super(e, accion);
    }
}

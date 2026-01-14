package cma.proyectocma.domain.mapper.exception;

import cma.proyectocma.domain.mapper.exception.base.MapperException;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public final class FromEntityPkDobleException extends MapperException {
    public FromEntityPkDobleException(Exception e) {
        super(e);
    }

    public FromEntityPkDobleException(RuntimeException e, Consumer<?> accion) {
        super(e, accion);
    }
}

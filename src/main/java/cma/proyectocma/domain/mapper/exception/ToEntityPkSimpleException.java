package cma.proyectocma.domain.mapper.exception;

import cma.proyectocma.domain.mapper.exception.base.MapperException;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public final class ToEntityPkSimpleException extends MapperException {
    public ToEntityPkSimpleException(Exception e) {
        super(e);
    }

    public ToEntityPkSimpleException(Exception e, Consumer<?> accion) {
        super(e, accion);
    }
}

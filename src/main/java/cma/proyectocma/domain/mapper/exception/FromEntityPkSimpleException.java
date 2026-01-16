package cma.proyectocma.domain.mapper.exception;

import cma.proyectocma.domain.mapper.exception.base.MapperException;
import lombok.Getter;

import java.util.function.Consumer;

@Getter
public final class FromEntityPkSimpleException extends MapperException {

    public FromEntityPkSimpleException(String message) {
        super(message);
    }

    public FromEntityPkSimpleException(Exception e) {
        super(e);
    }

    public FromEntityPkSimpleException(Exception e, Consumer<?> accion) {
        super(e, accion);
    }

}

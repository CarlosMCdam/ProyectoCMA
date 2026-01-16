package cma.proyectocma.domain.service.exception;

import cma.proyectocma.domain.service.exception.base.ServiceException;

import java.util.function.Consumer;

public final class ServicePkSimpleException extends ServiceException {

    public ServicePkSimpleException(Exception e) {
        super(e);
    }

    public ServicePkSimpleException(Exception e, Consumer<?> accion) {
        super(e, accion);
    }

}

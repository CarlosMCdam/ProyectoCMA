package cma.proyectocma.domain.service.exception;

import cma.proyectocma.domain.service.exception.base.ServiceException;

import java.util.function.Consumer;

public final class ServiceRegistrarException extends ServiceException {

    public ServiceRegistrarException(String message) {
        super(message);
    }

    public ServiceRegistrarException(Exception e) {
        super(e);
    }

    public ServiceRegistrarException(Exception e, Consumer<?> accion) {
        super(e, accion);
    }

}

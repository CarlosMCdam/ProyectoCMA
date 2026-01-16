package cma.proyectocma.domain.service.exception;

import cma.proyectocma.domain.service.exception.base.ServiceException;

import java.util.function.Consumer;

public final class ServicePkDobleException extends ServiceException {

    public ServicePkDobleException(String message) {
        super(message);
    }

    public ServicePkDobleException(Exception e) {
        super(e);
    }

    public ServicePkDobleException(Exception e, Consumer<?> accion) {
        super(e, accion);
    }

}

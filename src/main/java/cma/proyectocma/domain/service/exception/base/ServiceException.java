package cma.proyectocma.domain.service.exception.base;

import cma.proyectocma.domain.common.C;
import cma.proyectocma.domain.service.exception.ServicePkDobleException;
import cma.proyectocma.domain.service.exception.ServicePkSimpleException;
import cma.proyectocma.domain.service.exception.ServiceRegistrarException;

import java.util.function.Consumer;

public abstract sealed class ServiceException extends RuntimeException
        permits ServicePkSimpleException, ServicePkDobleException,
        ServiceRegistrarException {

    @SuppressWarnings(C.SONARQUBEFALSEPOSITIVE_UNUSED)
    private final transient Consumer<?> accion;

    protected ServiceException(String message) {
        super(message);
        this.accion = null;
    }

    protected ServiceException(Exception e) {
        super(e);
        this.accion = null;
    }

    protected ServiceException(Exception e, Consumer<?> accion) {
        super(e);
        this.accion = accion;
    }

}

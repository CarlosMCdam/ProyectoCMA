package cma.proyectocma.domain.service.exception.base;

import cma.proyectocma.domain.common.C;
import cma.proyectocma.domain.service.exception.ServicePkDobleException;
import cma.proyectocma.domain.service.exception.ServicePkSimpleException;

import java.util.function.Consumer;

public abstract sealed class ServiceException extends RuntimeException permits ServicePkSimpleException, ServicePkDobleException {

    @SuppressWarnings(C.SONARQUBEFALSEPOSITIVE_UNUSED)
    private final transient Consumer<?> accion;

    protected ServiceException(Exception e) {
        super(e);
        this.accion = null;
    }

    protected ServiceException(Exception e, Consumer<?> accion) {
        super(e);
        this.accion = accion;
    }

}

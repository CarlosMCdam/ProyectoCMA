package cma.proyectocma.ui.exception;

public class UiException extends RuntimeException {

    public UiException() {
        super("");
    }

    public UiException(String message) {
        super(message);
    }

    public UiException(Exception e) {
        super(e);
    }

}

package cma.proyectocma.ui.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AppExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(AppExceptionHandler.class);
    private static final StackWalker walker = StackWalker.getInstance();

    public static void handle(Throwable error) {
        walker.walk(frames ->
                frames.findFirst()
        ).ifPresent(frame -> {
            log.error(frame.getMethodName(), error);
        });
    }
}

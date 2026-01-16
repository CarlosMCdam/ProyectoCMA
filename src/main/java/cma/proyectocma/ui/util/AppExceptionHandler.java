package cma.proyectocma.ui.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(AppExceptionHandler.class);
    private static final StackWalker walker = StackWalker.getInstance();

    public static void handle(Thread throwerThread, Throwable throwable) {
        walker.walk(Stream::findFirst).ifPresent(frame ->
                log.error(frame.getMethodName(), throwerThread, throwable));
    }

}

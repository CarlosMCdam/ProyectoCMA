package cma.proyectocma.ui.util;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.util.concurrent.Callable;

public final class TaskLauncher<T> {
    public TaskLauncher(Callable<T> metodo, EventHandler<WorkerStateEvent> evento) {
        Task<T> task = new Task<>() {
            @Override
            protected T call() throws Exception {
                return metodo.call();
            }
        };
        task.setOnSucceeded(evento);
        new Thread(task).start();
    }
}

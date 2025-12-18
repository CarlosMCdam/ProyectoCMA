package cma.proyectocma;

import cma.proyectocma.ui.ApplicationCMA;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) {
        Application.launch(ApplicationCMA.class, args);
    }
}

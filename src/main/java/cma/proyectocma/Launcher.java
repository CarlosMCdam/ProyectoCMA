package cma.proyectocma;

import cma.proyectocma.ui.ApplicationCMA;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "cma.proyectocma",
        "cma.proyectocma.dao.repository.provider",
        "cma.proyectocma.domain.mapper.provider",
        "cma.proyectocma.domain.service.provider"
})
public final class Launcher {

    public static void main(String[] args) {
        Application.launch(ApplicationCMA.class, args);
    }

}

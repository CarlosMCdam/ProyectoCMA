package cma.proyectocma;

import cma.proyectocma.dao.repository.base.RepositoryPkSimple;
import cma.proyectocma.ui.ApplicationCMA;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public final class Launcher {
    public static void main(String[] args) {
        Application.launch(ApplicationCMA.class, args);
    }
}

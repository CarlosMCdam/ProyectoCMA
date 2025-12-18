package cma.proyectocma.ui;

import cma.proyectocma.Launcher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class ApplicationCMA extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        context = new SpringApplicationBuilder(Launcher.class).run();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(ApplicationCMA.class.getResource(
                "/cma/proyectocma/hello-view.fxml"
        ));
        loader.setControllerFactory(context::getBean);
        stage.setScene(new Scene(loader.load(), 640, 480));
        stage.setTitle("Hello!");
        stage.show();
    }

    @Override
    public void stop() {
        context.close();
    }
}

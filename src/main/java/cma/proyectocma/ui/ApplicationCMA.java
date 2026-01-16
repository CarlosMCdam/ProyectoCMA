package cma.proyectocma.ui;

import cma.proyectocma.Launcher;
import cma.proyectocma.ui.common.C;
import cma.proyectocma.ui.util.AppExceptionHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Arrays;

public final class ApplicationCMA extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        context = new SpringApplicationBuilder(Launcher.class).run();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> AppExceptionHandler.handle(thread, throwable));
        context.addApplicationListener(event -> {
            if (event instanceof ApplicationReadyEvent) Platform.runLater(() -> {
                FXMLLoader loader = new FXMLLoader(ApplicationCMA.class.getResource(
                        C.APP_PATH_FXML_ROOT
                ));
                loader.setControllerFactory(context::getBean);
                try {
                    stage.setScene(new Scene(loader.load(), 640, 480));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.show();
            });
        });
        Arrays.stream(context.getBeanDefinitionNames()).filter(name -> name.contains("Service") | name.contains("Mapper") | name.contains("Repository")).forEach(System.out::println);
    }

    @Override
    public void stop() {
        context.close();
    }

}

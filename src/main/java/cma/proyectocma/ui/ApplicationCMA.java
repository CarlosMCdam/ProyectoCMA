package cma.proyectocma.ui;

import cma.proyectocma.ui.common.C;
import cma.proyectocma.ui.screen.menu.MenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public final class ApplicationCMA extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new MenuController().init().getRootNode(), 1440, 720);
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(ApplicationCMA.class.getResourceAsStream(C.PATH_IMAGE_ICON))));
        stage.show();
    }

}


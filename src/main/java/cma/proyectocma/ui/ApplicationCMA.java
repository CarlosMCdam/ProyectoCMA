package cma.proyectocma.ui;

import cma.proyectocma.ui.common.C;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public final class ApplicationCMA extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new FXMLLoader(ApplicationCMA.class.getResource(C.PATH_FXML_ROOT)).load(), 840, 580);
        Font font = Font.loadFont(
                getClass().getResource("/cma/proyectocma/style/fonts/Inter/static/Inter_18pt-Regular.ttf")
                        .toExternalForm(), 14
        );
        scene.getStylesheets().add(Objects.requireNonNull(ApplicationCMA.class.getResource(C.PATH_STYLE_THEME)).toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(ApplicationCMA.class.getResourceAsStream(C.PATH_IMAGE_ICON))));
        stage.show();
    }

}


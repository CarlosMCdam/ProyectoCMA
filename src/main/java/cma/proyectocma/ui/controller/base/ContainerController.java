package cma.proyectocma.ui.controller.base;

import cma.proyectocma.ui.controller.listado.ListadoTipo;
import cma.proyectocma.ui.controller.menu.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public abstract sealed class ContainerController permits MenuController {

    protected <C extends ContainerController, E extends EmbeddedController<C>, P extends Pane> E loadEmbeddedController(
            C containerController, ListadoTipo listadoTipo, String fxmlPath, P containerPane
    ) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            E controller = loader.getController();
            controller.init(containerController, listadoTipo);
            containerPane.getChildren().setAll(root);
            return controller;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

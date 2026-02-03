package cma.proyectocma.ui.controller.menu;

import cma.proyectocma.ui.common.C;
import cma.proyectocma.ui.controller.base.ContainerController;
import cma.proyectocma.ui.controller.detalle.DetalleController;
import cma.proyectocma.ui.controller.listado.ListadoController;
import cma.proyectocma.ui.controller.listado.ListadoTipo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Arrays;

public final class MenuController extends ContainerController {

    @FXML
    private TabPane tabs;
    @FXML
    private StackPane listadoPane;
    @FXML
    private StackPane detallePane;

    @FXML
    public void initialize() {
        this.tabs.getTabs().addAll(Arrays.stream(ListadoTipo.values()).map(tipo -> {
            Tab tab = new Tab(tipo.getNombre());
            tab.setUserData(tipo);
            return tab;
        }).toList());

        this.tabs.getSelectionModel().selectedItemProperty().addListener((_, _, newTab) -> {
            if (newTab == null) throw new RuntimeException();
            System.out.println("event listener de los tabs" + newTab.getUserData().toString());
            cargarListado((ListadoTipo) newTab.getUserData());
        });

        this.detallePane.setVisible(false);
    }

    public void ocultarDetalle() {
        detallePane.setVisible(false);
    }

    public void recargarListado() {
        // Obtiene el tipo del tab seleccionado
        ListadoTipo tipo = (ListadoTipo) tabs.getSelectionModel()
                .getSelectedItem()
                .getUserData();

        cargarListado(tipo);
    }

    private void cargarListado(ListadoTipo tipo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(C.PATH_FXML_LISTADO));
            Parent root = loader.load(); // deja que el FXML cree el controlador
            ListadoController controller = loader.getController();
            System.out.println("Loaded listado.fxml root=" + root + " controller=" + controller);
            if (controller == null) throw new IllegalStateException("ListadoController es null tras load()");
            controller.init(this, tipo);
            listadoPane.getChildren().setAll(root);
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        }
    }


    public void cargarDetalle(Record item, ListadoTipo tipoListado) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(C.PATH_FXML_DETALLE));
            Parent root = loader.load();
            DetalleController controller = loader.getController();
            controller.init(this, tipoListado);
            controller.setData(item);
            detallePane.getChildren().setAll(root);
            detallePane.setVisible(true);
        } catch (IOException e) {
            throw new RuntimeException("Error cargando detalle", e);
        }
    }

}

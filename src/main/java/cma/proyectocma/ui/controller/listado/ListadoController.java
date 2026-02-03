package cma.proyectocma.ui.controller.listado;

import cma.proyectocma.ui.common.C;
import cma.proyectocma.ui.controller.base.EmbeddedController;
import cma.proyectocma.ui.controller.detalle.DetalleController;
import cma.proyectocma.ui.controller.menu.MenuController;
import cma.proyectocma.ui.util.ServiceRegistry;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public final class ListadoController extends EmbeddedController<MenuController> {

    private static final Logger LOG = Logger.getLogger(ListadoController.class.getName());

    @Setter
    private ListadoTipo listadoTipo;

    @FXML
    private ListView<Object> listaDtos;

    @Override
    public void init(MenuController menuController, ListadoTipo listadoTipo) {
        System.out.println("listaDtos = " + listaDtos);
        LOG.info("Inicializando listado de listadoTipo: " + listadoTipo);
        setContainerController(menuController);
        configurarCeldas();
        this.listadoTipo = listadoTipo;
        List<?> lista = ServiceRegistry.getService(listadoTipo).findAll();
        listaDtos.setItems(FXCollections.observableArrayList(lista));

    }

    private void configurarCeldas() {
        listaDtos.setCellFactory(_ -> new ListCell<>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                System.out.println("updateItem -> item=" + item + " empty=" + empty);
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                    return;
                }
                setGraphic(createItemGraphic(item));
            }
        });
    }

    private FXMLLoader loadFxml(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            loader.load();
            return loader;
        } catch (IOException e) {
            LOG.severe("Error cargando FXML '" + path + "': " + e.getMessage());
        }
        return null;
    }

    private Parent createItemGraphic(Object item) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(C.PATH_FXML_LISTADO_ITEM));
            Parent root = loader.load();
            Object ctrl = loader.getController();
            System.out.println("listado-item loaded root=" + root + " controller=" + ctrl + " item=" + item);
            if (ctrl == null) throw new IllegalStateException("ListadoItemController es null");
            ListadoItemController listadoItemController = (ListadoItemController) ctrl;
            listadoItemController.setItem((Record) item);
            listadoItemController.setData();
            styleItemRoot(root);
            root.setOnMouseClicked(_ -> abrirDetalle((Record) item));
            return root;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void applyItemController(ListadoItemController listadoItemController, Record item) {
        listadoItemController.setItem(item);
        listadoItemController.setData();
    }

    private void styleItemRoot(Parent root) {
        root.setStyle(root.getStyle() + "-fx-cursor: hand;");
    }

    private void abrirDetalle(Record item) {
        FXMLLoader loader = loadFxml(C.PATH_FXML_DETALLE);
        if (loader == null) return;
        DetalleController controller = loader.getController();
        controller.setListadoTipo(listadoTipo);
        getContainerController().cargarDetalle(item, listadoTipo);
    }

}

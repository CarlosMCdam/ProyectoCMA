package cma.proyectocma.ui.screen.listado;

import cma.proyectocma.ui.screen.base.EmbeddedController;
import cma.proyectocma.ui.screen.menu.MenuController;
import cma.proyectocma.ui.util.ServiceRegistry;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Setter;

public final class ListadoController extends EmbeddedController<ScrollPane, ListadoPainter, MenuController> {

    @Setter
    private ListadoTipo listadoTipo;

    @Override
    protected void initInner() {
        setPainter(new ListadoPainter());
        setRootNode(getPainter().rootNode());
    }

    public ListadoController init(MenuController menuController, ListadoTipo listadoTipo) {
        initInner();
        setContainerController(menuController);
        this.listadoTipo = listadoTipo;
        configurarListado();
        return this;
    }

    private void configurarListado() {
        VBox listado = getPainter().listado();
        ServiceRegistry.getService(listadoTipo).findAll().forEach(item -> {
            HBox listItem = loadItem(item);
            listItem.setOnMouseClicked(ignored -> {
                getContainerController().loadDetail(item, listadoTipo);
            });
            listado.getChildren().add(listItem);
        });
        getRootNode().setContent(listado);
    }

    private HBox loadItem(Object item) {
        return loadController(ListadoItemController.class)
                .init(getContainerController(), (Record) item)
                .getRootNode();
    }

}

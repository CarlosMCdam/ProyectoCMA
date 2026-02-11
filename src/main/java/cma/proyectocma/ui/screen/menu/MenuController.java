package cma.proyectocma.ui.screen.menu;

import cma.proyectocma.ui.screen.base.Controller;
import cma.proyectocma.ui.screen.detalle.DetalleController;
import cma.proyectocma.ui.screen.listado.ListadoController;
import cma.proyectocma.ui.screen.listado.ListadoTipo;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.Arrays;

public final class MenuController extends Controller<BorderPane, MenuPainter> {

    private HBox tabs;

    @Override
    protected void initInner() {
        setPainter(new MenuPainter());
        setRootNode(getPainter().rootNode());
        tabs = getPainter().tabs();
    }

    public MenuController init() {
        initInner();
        getRootNode().sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                getRootNode().prefWidthProperty().bind(newScene.widthProperty());
                getRootNode().prefHeightProperty().bind(newScene.heightProperty());
            }
        });

        tabs.getChildren().addAll(Arrays.stream(ListadoTipo.values()).map(listadoTipo -> {
            Button tab = getPainter().tab(listadoTipo.getNombre());
            tab.setOnAction(ignored -> loadList(listadoTipo));
            return tab;
        }).toList());
        getRootNode().setTop(tabs);
        return this;
    }

    public void loadList(ListadoTipo listadoTipo) {
        getRootNode().setCenter(loadController(ListadoController.class)
                .init(this, listadoTipo)
                .getRootNode()
        );
    }

    public void loadDetail(Record item, ListadoTipo listadoTipo) {
        getRootNode().setBottom(loadController(DetalleController.class)
                .init(this, item, listadoTipo)
                .getRootNode()
        );
    }

}

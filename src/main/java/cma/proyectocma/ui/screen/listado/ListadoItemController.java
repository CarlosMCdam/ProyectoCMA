package cma.proyectocma.ui.screen.listado;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.ui.exception.UiException;
import cma.proyectocma.ui.screen.base.Controller;
import cma.proyectocma.ui.screen.base.EmbeddedController;
import cma.proyectocma.ui.screen.menu.MenuController;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.List;

@Setter
public final class ListadoItemController extends EmbeddedController<HBox, ListadoItemPainter, MenuController> {

    private Record item;

    @Override
    protected void initInner() {
        setPainter(new ListadoItemPainter());
        setRootNode(getPainter().rootNode());
    }

    public ListadoItemController init(MenuController containerController, Record item) {
        initInner();
        setContainerController(containerController);
        this.item = item;
        getRootNode().getChildren().addAll(attributeContainers());
        return this;
    }

    private List<VBox> attributeContainers() {
        return Arrays.stream(item.getClass().getRecordComponents()).map(component -> {
            if (component.getDeclaredAnnotation(DtoId.class) != null) return idContainer(component);
            return normalContainer(component);
        }).toList();
    }

    private VBox idContainer(RecordComponent component) {
        try {
            VBox idContainer = getPainter().idContainer();
            Object value = component.getAccessor().invoke(item);
            idContainer.getChildren().addAll(
                    getPainter().idName(textFormatter(component.getName()).toUpperCase()),
                    getPainter().idValue(value != null ? value.toString() : "null")
            );
            return idContainer;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new UiException(e);
        }
    }

    private VBox normalContainer(RecordComponent component) {
        try {
            VBox attributeContainer = getPainter().attributeContainer();
            Object value = component.getAccessor().invoke(item);
            attributeContainer.getChildren().addAll(
                    getPainter().attributeName(textFormatter(component.getName()).toUpperCase()),
                    getPainter().attributeValue(value != null ? value.toString() : "---")
            );
            return attributeContainer;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new UiException(e);
        }
    }

}


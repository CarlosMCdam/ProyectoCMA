package cma.proyectocma.ui.controller.listado;

import cma.proyectocma.domain.model.util.DtoId;
import cma.proyectocma.ui.common.C;
import cma.proyectocma.ui.controller.base.EmbeddedController;
import cma.proyectocma.ui.controller.menu.MenuController;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Setter
public final class ListadoItemController extends EmbeddedController<MenuController> {

    private Record item;

    @FXML
    private HBox attributesContainer;

    @Override
    public void init(MenuController containerController, ListadoTipo listadoTipo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setData() {
        System.out.println("ListadoItemController.setData item=" + item + " attributesContainer=" + attributesContainer);
        attributesContainer.getChildren().addAll(attributesContainers());
    }


    private List<VBox> attributesContainers() {
        return Arrays.stream(item.getClass().getRecordComponents()).map(component -> {
            if (component.getDeclaredAnnotation(DtoId.class) != null) return idContainer(component);
            return normalContainer(component);
        }).toList();
    }

    private VBox idContainer(RecordComponent component) {
        return attributeContainer(component, (attribute, container) -> {
            try {
                Object value = component.getAccessor().invoke(item);
                container.setBackground(newBackground(Color.WHITE));
                container.setPadding(new Insets(C.NODE_PADDING_DEFAULT));
                container.getChildren().addAll(
                        newLabel(component.getName().toUpperCase(), label -> {
                            label.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: black");
                            label.setAlignment(Pos.CENTER);
                        }),
                        newLabel(value != null ? value.toString() : "null", label -> {
                            label.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: black");
                            label.setAlignment(Pos.CENTER);
                            label.setPadding(newInsets(0, C.NODE_PADDING_DEFAULT * 2));
                            VBox.setVgrow(label, Priority.ALWAYS);
                            label.setMaxWidth(Double.MAX_VALUE);
                        })
                );
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    private VBox normalContainer(RecordComponent component) {
        return attributeContainer(component, (attribute, container) -> {
            try {
                Object value = component.getAccessor().invoke(item);
                container.setPadding(new Insets(C.NODE_PADDING_DEFAULT));
                container.setBackground(newBackground(Color.rgb(255, 255, 255, 0.1)));
                container.getChildren().addAll(
                        newLabel((component.getName()).toUpperCase(), label -> {
                            label.setStyle("-fx-font-size: 18px");
                            VBox.setVgrow(label, Priority.ALWAYS);
                            label.setMaxWidth(Double.MAX_VALUE);
                        }),
                        newLabel(value != null ? value.toString() : "---", label -> {
                            label.setStyle("-fx-font-size: 14px");
                            label.setAlignment(Pos.CENTER);
                            label.setBackground(newBackground(Color.rgb(255, 255, 255, 0.05)));
                            label.setPadding(newInsets(2, C.NODE_PADDING_DEFAULT));
                            VBox.setVgrow(label, Priority.ALWAYS);
                            label.setMaxWidth(Double.MAX_VALUE);
                        })
                );
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    private VBox attributeContainer(RecordComponent component, BiConsumer<RecordComponent, VBox> custom) {
        VBox attributeContainer = new VBox(C.NODE_VBOX_SPACING_DEFAULT);
        if (custom != null) custom.accept(component, attributeContainer);
        return attributeContainer;
    }

    private Insets newInsets(double verticalPadding, double horizontalPadding) {
        return new Insets(verticalPadding, horizontalPadding, verticalPadding, horizontalPadding);
    }

    private Label newLabel(String text, Consumer<Label> custom) {
        Label label = new Label(text);
        if (custom != null) custom.accept(label);
        return label;
    }

    private Background newBackground(Color color) {
        return new Background(new BackgroundFill(color, new CornerRadii(5), Insets.EMPTY));
    }

}


package cma.proyectocma.ui.screen.detalle;

import cma.proyectocma.ui.common.C;
import cma.proyectocma.ui.screen.base.Painter;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DetallePainter extends Painter<BorderPane> {

    @Override
    protected BorderPane rootNode() {
        return NodeStyler.of(new BorderPane())
                .min(Region.USE_PREF_SIZE, Region.USE_COMPUTED_SIZE)
                .background(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)))
                .padding(customInsets(0, C.NODE_PADDING_DEFAULT))
                .getNode();
    }

    HBox attributesContainer() {
        return NodeStyler.of(new HBox(C.NODE_SPACING_DEFAULT))
                .background(Background.EMPTY)
                .padding(new Insets(C.NODE_PADDING_DEFAULT))
                .getNode();
    }

    VBox attributeContainer() {
        return NodeStyler.of(new VBox(C.NODE_SPACING_DEFAULT))
                .max(Region.USE_COMPUTED_SIZE)
                .getNode();
    }

    Label idValue(String value) {
        return NodeStyler.of(new Label(value))
                .background(customBackground(Color.WHITE))
                .padding(new Insets(C.NODE_PADDING_DEFAULT * 1.5))
                .font(Font.font(C.FONT_SEGOEUI, FontWeight.EXTRA_BOLD, 20))
                .textFill(Color.BLACK)
                .getNode();
    }

    Label attributeName(String text) {
        return NodeStyler.of(new Label(text))
                .font(Font.font(C.FONT_SEGOEUI, FontWeight.BOLD, 15))
                .textFill(Color.WHITE)
                .getNode();
    }

    TextField attributeValue(String text) {
        return NodeStyler.of(new TextField(text))
                .max(Region.USE_COMPUTED_SIZE)
                .also(attributeValue -> attributeValue.setPrefColumnCount(attributeValue.getText().length()))
                .getNode();
    }

    HBox optionsContainer() {
        return NodeStyler.of(new HBox(C.NODE_SPACING_DEFAULT))
                .padding(new Insets(C.NODE_PADDING_DEFAULT * 3.0))
                .getNode();
    }

    Button guardar() {
        return NodeStyler.of(new Button("GUARDAR"))
                .pref(Region.USE_COMPUTED_SIZE)
                .background(customBackground(Color.WHITE))
                .font(Font.font(C.FONT_SEGOEUI, FontWeight.BOLD, 15))
                .textFill(Color.BLACK)
                .hover(true)
                .getNode();
    }

    Button cancelar() {
        return NodeStyler.of(new Button("CANCELAR"))
                .pref(Region.USE_COMPUTED_SIZE)
                .background(customBackground(Color.rgb(255, 255, 255, 0.25)))
                .font(Font.font(C.FONT_SEGOEUI, FontWeight.NORMAL, 15))
                .textFill(Color.BLACK)
                .hover(false)
                .getNode();
    }

}
